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
                
        points = row.find_all('td', class_='points')
        if points[0]:
            live_points = points[0].get_text(strip=True)
        else:
            live_points = ''
        if points[1]:
            off_points = points[1].get_text(strip=True)
        else:
            off_points = ''

        diff_points = live_points - off_points

        best_points = row.find('td', class_='best')
        if best_points:
            best_points = best_points.get_text(strip=True)
        else:
            best_points = ''

        tournament = row.find_all('span', class_= ['name', 'desc'])
        if tournament:
            current_tournament = tournament[0] + " - " + tournament[1]
        else:
            current_tournament = ''
        
        diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
        diff = diff_elem.get_text(strip=True) if diff_elem else ''
        if diff:
            diff = '+' + diff if int(diff) > 0 else diff

        overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
        overview_url = overview_elem['href'] if overview_elem else ''

        flag_elem = row.find('img', class_='flag')
        flag_url = flag_elem['src'] if flag_elem else ''

        if name != '' and name != '-' and rank != '' and points != '':
            player = {
                "rank" : rank,
                "name" : name,
                "live_points": live_points,
                "official_points": off_points,
                "diff_points": diff_points,
                "max_points": best_points,
                "current_tournament": current_tournament,
                "diff": diff, 
                "overview_url": overview_url,
                "flag_url": flag_url
            }
            leaderboard_players.append(player)

        if len(leaderboard_players) == 10:
            break


# def getLeaderboardATPLive():
    
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
                    # "max_points": max_points,
                    # "diff_points": diff_points,
                    # "current_tournament": current_tournament,
                    "diff": diff, 
                    "overview_url": overview_url,
                    "flag_url": flag_url
            }
            leaderboard_players.append(leaderboard_player)

    json_official_leaderboard = json.dumps(leaderboard_players, indent=4)
    print (json_official_leaderboard)
        