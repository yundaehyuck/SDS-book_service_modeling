# -*- encoding: utf-8 -*-
"""text_classification_modeling.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1mNN7z3wPlyvWf4PrsK5amaoEHMhbzFRh

# load library
"""
import torch
from torch import nn
import torch.nn.functional as F
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
import gluonnlp as nlp
import numpy as np
from transformers import BertModel
from kobert_tokenizer import KoBERTTokenizer
import openai

"""# load pretrained model"""
tokenizer = KoBERTTokenizer.from_pretrained('skt/kobert-base-v1')
bertmodel = BertModel.from_pretrained('skt/kobert-base-v1')

"""# define model"""

class BERTClassifier(nn.Module):
    def __init__(self,
                 bert,
                 hidden_size = 768,
                 num_classes=2,
                 dr_rate=None,
                 params=None):
        super(BERTClassifier, self).__init__()
        self.bert = bert
        self.dr_rate = dr_rate
                 
        self.classifier = nn.Linear(hidden_size , num_classes)
        if dr_rate:
            self.dropout = nn.Dropout(p=dr_rate)

    def forward(self, token_ids, attention_mask):        
        pooler = self.bert(input_ids = token_ids, attention_mask = attention_mask).pooler_output
        if self.dr_rate:
            out = self.dropout(pooler)
        else:
            out = pooler
        return self.classifier(out)

star_model = BERTClassifier(bertmodel, num_classes=5, dr_rate = 0.5)

"""load trained model"""

resume = "./star/model.pth"

checkpoint = torch.load(resume, map_location=torch.device('cpu'))

star_model.load_state_dict(checkpoint)

#prediction review star point
"""
input: chatgpt review
output: string of star point(one of 1,2,3,4,5)
"""
def predict_star_point(review):
    
    #simple preprocessing review
    review = review.strip("\n").strip(" ")

    #transform review
    transform_review = tokenizer.batch_encode_plus([review],max_length=128,pad_to_max_length=True)

    #prepare input data
    token_ids = torch.tensor(transform_review['input_ids']).long()
    attention_mask = torch.tensor(transform_review['attention_mask']).long()

    #prediction
    output = star_model(token_ids, attention_mask)

    #for confidence
    #percentage_output = F.softmax(output, dim = 1)

    #get maximum confidence class
    pred = output.cpu().detach().numpy()
    sorted_pred = np.argsort(pred,axis = 1)
    return str(sorted_pred[0][-1]+1)

"""
input: title, words
output: review, star point
"""
def create_gpt_review(title:str, words: str, writer: str, length: int):
    
    # message 구성    
    m = f"너는 {writer}의 {title}이라는 책을 읽은 사람이야. 너에 대한 자기소개는 하지 말고 {words}를 키워드로 해서 서평, 키워드라는 말을 쓰지 말고 서평을 {length}자 이내로 써줘"
    
    #chatgpt request
    completion = openai.ChatCompletion.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "user", "content": m}
    ]
    )
    
    #chatgpt response
    response = completion.choices[0].message['content']
    
    #predicted star point
    star = predict_star_point(response)
    
    return response,star