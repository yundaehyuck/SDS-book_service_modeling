import json
from selenium import webdriver
from bs4 import BeautifulSoup
import time

path = "chromedriver.exe"
driver = webdriver.Chrome(path)

url = "https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=309295168"
driver.get(url)

driver.execute_script("window.scrollTo(0, 4500)") 

html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')

# iframe = driver.find_element_by_xpath("/html/body/div/div/div/iframe")

# driver.switch_to.frame(iframe)


# while 1:
#     time.sleep(0.5)
#     try:
#         x = driver.find_element_by_xpath('//*[@id="divReviewPageMore"]/div[1]/a')
#         driver.execute_script("window.scrollTo(0, 200)") 
#         x.click()
    
#     except:
#         break

html = driver.page_source
soup = BeautifulSoup(html, 'html.parser')

y = soup.find_all("div","HL_star")

x = driver.find_elements_by_xpath('//*[@id="CommentReviewList"]/div[1]')[0]

print(x.text.split("\nThanks to\n공감"))
