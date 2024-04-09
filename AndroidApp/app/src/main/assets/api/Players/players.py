import re
import requests
from bs4 import BeautifulSoup

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}

url = 'https://tnnslive.com/player/225050'

response = requests.get(url, headers=headers)

if response.status_code == 200:
    html = response.text

    soup = BeautifulSoup(html, 'html.parser')

    age_span = soup.find('div', string='Age').find_next('div').get_text(strip=True)

    weight_span = details.find('span', string=re.compile(r'\d+ lbs \(\d+kg\)'))
    weight = weight_span.text

    height_span = details.find('span', string=re.compile(r'\d+\' \d+" \(\d+cm\)'))
    height = height_span.text

    turned_pro_span = details.find('span', string=re.compile(r'\d{4}'))
    turned_pro = turned_pro_span.text


