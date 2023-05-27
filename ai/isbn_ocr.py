from paddleocr import PaddleOCR

#only once to download and load model into memory
ocr = PaddleOCR(use_angle_cls = True, lang = "en")

