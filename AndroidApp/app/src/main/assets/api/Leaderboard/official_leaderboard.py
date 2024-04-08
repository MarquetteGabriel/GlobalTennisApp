import requests, json
from bs4 import BeautifulSoup

url_all = 'https://www.atptour.com/en/rankings/singles?RankRange=0-5000&Region=all&DateWeek=Current%20Week'

url_top100 = 'https://www.atptour.com/en/rankings/singles?RankRange=0-100&Region=all&DateWeek=Current%20Week'
url_101_200 = 'https://www.atptour.com/en/rankings/singles?RankRange=101-200&Region=all&DateWeek=Current%20Week'
url_201_300 = 'https://www.atptour.com/en/rankings/singles?RankRange=201-300&Region=all&DateWeek=Current%20Week'
url_301_400 = 'https://www.atptour.com/en/rankings/singles?RankRange=301-400&Region=all&DateWeek=Current%20Week'
url_401_500 = 'https://www.atptour.com/en/rankings/singles?RankRange=401-500&Region=all&DateWeek=Current%20Week'
url_501_600 = 'https://www.atptour.com/en/rankings/singles?RankRange=501-600&Region=all&DateWeek=Current%20Week'
url_601_700 = 'https://www.atptour.com/en/rankings/singles?RankRange=601-700&Region=all&DateWeek=Current%20Week'
url_701_800 = 'https://www.atptour.com/en/rankings/singles?RankRange=701-800&Region=all&DateWeek=Current%20Week'
url_801_900 = 'https://www.atptour.com/en/rankings/singles?RankRange=801-900&Region=all&DateWeek=Current%20Week'
url_901_1000 = 'https://www.atptour.com/en/rankings/singles?RankRange=901-1000&Region=all&DateWeek=Current%20Week'
url_1001_1100 = 'https://www.atptour.com/en/rankings/singles?RankRange=1001-1100&Region=all&DateWeek=Current%20Week'
url_1101_1200 = 'https://www.atptour.com/en/rankings/singles?RankRange=1101-1200&Region=all&DateWeek=Current%20Week'
url_1201_1300 = 'https://www.atptour.com/en/rankings/singles?RankRange=1201-1300&Region=all&DateWeek=Current%20Week'
url_1301_1400 = 'https://www.atptour.com/en/rankings/singles?RankRange=1301-1400&Region=all&DateWeek=Current%20Week'
url_1401_1500 = 'https://www.atptour.com/en/rankings/singles?RankRange=1401-1500&Region=all&DateWeek=Current%20Week'
url_1501_all = 'https://www.atptour.com/en/rankings/singles?RankRange=1501-5000&Region=all&DateWeek=Current%20Week'

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}


def getLeaderboardATPOfficialTop100():
    
    response = requests.get(url_top100, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []
        getTop10ATP(leaderboard_players, soup)

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard

def getLeaderboardATPOfficialTop200():
    
    response = requests.get(url_101_200, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop300():
    
    response = requests.get(url_201_300, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop400():
    
    response = requests.get(url_301_400, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop500():
    
    response = requests.get(url_401_500, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop600():
    
    response = requests.get(url_501_600, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop700():
    
    response = requests.get(url_601_700, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop800():
    
    response = requests.get(url_701_800, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop900():
    
    response = requests.get(url_801_900, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1000():
    
    response = requests.get(url_901_1000, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1100():
    
    response = requests.get(url_1001_1100, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1200():
    
    response = requests.get(url_1101_1200, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1300():
    
    response = requests.get(url_1201_1300, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1400():
    
    response = requests.get(url_1301_1400, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop1500():
    
    response = requests.get(url_1401_1500, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTop5000():
    
    response = requests.get(url_1501_all, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getLeaderboardATPOfficialTopAll():
    
    response = requests.get(url_all, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', class_='lower-row')

        for row in rows:
            rank = row.find('td', class_='rank').get_text(strip=True)

            name = row.find('span', class_='lastName')
            if name:
                name = name.get_text(strip=True)
            else:
                name = ''

            points = row.find('td', class_='points').get_text(strip=True) if row.find('td', class_='points') else ''

            diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
            diff = diff_elem.get_text(strip=True) if diff_elem else ''
            if diff:
                diff = '+' + diff if int(diff) > 0 else diff

            overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
            overview_url = overview_elem['href'] if overview_elem else ''

            flag_elem = row.find('img', class_='flag')
            flag_url = flag_elem['src'] if flag_elem else ''

            if name!= '':
                leaderboard_player = {
                        "rank" : rank,
                        "name" : name,
                        "points": points,
                        "diff": diff, 
                        "overview_url": overview_url,
                        "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return json_official_leaderboard
    
def getTop10ATP(leaderboard_players, soup):
    rows = soup.find_all('tr', {'class': ''})

    for row in rows:
        
        rank = row.find('td', class_='rank')
        if rank:
            rank = rank.get_text(strip=True)
        else:
            rank = ''
        
        name = row.find('span', class_=None)
        if name:
            name = name.get_text(strip=True)
        else:
            name = ''
                
        points = row.find('td', class_='points')
        if points:
            points = points.get_text(strip=True)
        else:
            points = ''
        
        diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
        diff = diff_elem.get_text(strip=True) if diff_elem else ''
        if diff:
            diff = '+' + diff if int(diff) > 0 else diff

        overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
        overview_url = overview_elem['href'] if overview_elem else ''

        flag_elem = row.find('img', class_='flag')
        flag_url = flag_elem['src'] if flag_elem else ''

        if name != '' and rank != '' and points != '':
            player = {
                "rank" : rank,
                "name" : name,
                "points": points,
                "diff": diff, 
                "overview_url": overview_url,
                "flag_url": flag_url
            }
            leaderboard_players.append(player)

        if len(leaderboard_players) == 10:
            break