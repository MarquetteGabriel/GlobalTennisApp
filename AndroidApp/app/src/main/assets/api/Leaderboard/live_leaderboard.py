import requests, json
from bs4 import BeautifulSoup

url_all = 'https://www.atptour.com/en/rankings/singles?RankRange=0-5000&Region=all'

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}

url_top100 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=0-100&Region=all'
url_101_200 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=101-200&Region=all'
url_201_300 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=201-300&Region=all'
url_301_400 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=301-400&Region=all'
url_401_500 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=401-500&Region=all'
url_501_600 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=501-600&Region=all'
url_601_700 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=601-700&Region=all'
url_701_800 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=701-800&Region=all'
url_801_900 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=801-900&Region=all'
url_901_1000 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=901-1000&Region=all'
url_1001_1100 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1001-1100&Region=all'
url_1101_1200 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1101-1200&Region=all'
url_1201_1300 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1201-1300&Region=all'
url_1301_1400 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1301-1400&Region=all'
url_1401_1500 = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1401-1500&Region=all'
url_1501_all = 'https://www.atptour.com/en/rankings/singles/live?RankRange=1501-5000&Region=all'

def getTop10ATP(leaderboard_players, soup):
    rows = soup.find_all('tr', {'class': ['', 'in-contention']})

    for row in rows:
        
        rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
        diff = row.find('span', class_=['rank-up', 'rank-down'])
        if diff:
            diff_rank = diff.text.strip()
            diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
        else:
            diff_rank = ''

        player = row.find('td', class_='player bold heavy large-cell')
        name = player.find('span', class_='lastName').text.strip()

        live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
        parts = live_points_diff.split(sep=None)
        parts = list(filter(None, parts))

        live_points = int(parts[0].replace(',', ''))
        if len(parts) > 1 and parts[1] != '-':
            diff_points = int(parts[1])
        else:
            diff_points = 0


        off_points = live_points - diff_points

        max_points = int(row.find('span', class_='max-points').text.strip().replace(',', ''))
        next_points = int(row.find('span', class_='next-points').text.strip().replace(',', ''))



        current_tournament = row.find_all('span', class_=['name', 'desc'])[0].text.strip() + " - " + row.find_all('span', class_=['name', 'desc'])[1].text.strip()
        overview_url = player.a['href']

        flag_url = player.img['src']

        if name != '':
            player = {
                "rank" : rank,
                "name" : name,
                "live_points": live_points,
                "official_points": off_points,
                "diff_points": diff_points,
                "next_points": next_points,
                "max_points": max_points,
                "current_tournament": current_tournament,
                "diff_rank": diff_rank, 
                "overview_url": overview_url,
                "flag_url": flag_url
            }
            leaderboard_players.append(player)

        if len(leaderboard_players) == 10:
            break


def getLeaderboardATPLiveTop100():
    
    response = requests.get(url_top100, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []
        getTop10ATP(leaderboard_players, soup)

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)

def getLeaderboardATPLiveTop200():
    
    response = requests.get(url_101_200, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
    
def getLeaderboardATPLiveTop300():
    
    response = requests.get(url_201_300, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
    
def getLeaderboardATPLiveTop400():
    
    response = requests.get(url_301_400, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)  

def getLeaderboardATPLiveTop500():
    
    response = requests.get(url_401_500, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0

            off_points = live_points - diff_points

            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''

            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)
        
def getLeaderboardATPLiveTop600():
    
    response = requests.get(url_501_600, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop700():
    
    response = requests.get(url_601_700, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop800():
    
    response = requests.get(url_701_800, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop900():
    
    response = requests.get(url_801_900, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop1000():
    
    response = requests.get(url_901_1000, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop1100():
    
    response = requests.get(url_1001_1100, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop1200():
    
    response = requests.get(url_1101_1200, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)                   

def getLeaderboardATPLiveTop1300():
    
    response = requests.get(url_1201_1300, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)                 

def getLeaderboardATPLiveTop1400():
    
    response = requests.get(url_1301_1400, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop1500():
    
    response = requests.get(url_1401_1500, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveTop5000():
    
    response = requests.get(url_1501_all, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 
def getLeaderboardATPLiveAll():
    
    response = requests.get(url_all, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')

        leaderboard_players = []

        rows = soup.find_all('tr', {'class': ['lower-row']})

        for row in rows:
            rank = row.find('td', class_='rank bold heavy tiny-cell').text.strip()
            diff = row.find('span', class_=['rank-up', 'rank-down'])
            if diff:
                diff_rank = diff.text.strip()
                diff_rank = '+' + diff_rank if int(diff_rank) > 0 else diff_rank
            else:
                diff_rank = ''

            player = row.find('td', class_='player bold heavy large-cell')
            name = player.find('span', class_='lastName')
            if name:
                name = name.text.strip()
            else:
                name = ''

            live_points_diff = row.find('td', class_='points center bold extrabold small-cell').text.strip()
            parts = live_points_diff.split(sep=None)
            parts = list(filter(None, parts))

            live_points = int(parts[0].replace(',', ''))
            if len(parts) > 1 and parts[1] != '-':
                diff_points = int(parts[1])
            else:
                diff_points = 0


            off_points = live_points - diff_points


            max_points_diff = row.find('span', class_='max-points').text.strip()
            if max_points_diff != '-':
                max_points = int(max_points_diff.replace(',', ''))
            else:
                max_points = 0

            next_points_diff = row.find('span', class_='next-points').text.strip()
            if next_points_diff != '-':
                next_points = int(next_points_diff.replace(',', ''))
            else:
                next_points = 0

            current_tournament_diff = row.find_all('span', class_=['name', 'desc'])
            if len(current_tournament_diff) > 1:
                current_tournament = current_tournament_diff[0].text.strip() + " - " + current_tournament_diff[1].text.strip()
            else:
                current_tournament = ''
            overview_url = player.a['href']

            flag_url = player.img['src']

            if name!= '':
                leaderboard_player = {
                    "rank" : rank,
                    "name" : name,
                    "live_points": live_points,
                    "official_points": off_points,
                    "diff_points": diff_points,
                    "next_points": next_points,
                    "max_points": max_points,
                    "current_tournament": current_tournament,
                    "diff_rank": diff_rank, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
                }
                leaderboard_players.append(leaderboard_player)

        json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
        return (json_official_leaderboard)   
                 


