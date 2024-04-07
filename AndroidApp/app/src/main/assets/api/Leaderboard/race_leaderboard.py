import requests, json
from bs4 import BeautifulSoup

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}

url = 'https://www.atptour.com/en/rankings/singles-race-to-turin'

def getLeaderboardATPRace():

    response = requests.get(url, headers=headers)

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

            points = row.find('td', class_='best').get_text(strip=True) if row.find('td', class_='points') else ''

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
        return (json_official_leaderboard)


def getTop10ATP(leaderboard_players, soup):

    rows = soup.find_all('tr', {'class': ''})

    for row in rows:
        rank = row.find('td', class_='rank')
        if rank:
            rank = rank.get_text(strip=True)
        else:
            rank = ''

        # trouver l'élément a qui contient l'attribut href avec "/overview"
        overview_elem = row.find('a', {'href': lambda x: x and '/overview' in x})
        if overview_elem:
            # trouver l'élément span enfant qui contient le nom du joueur
            name_elem = overview_elem.find('span')
            if name_elem:
                name = name_elem.get_text(strip=True)
            else:
                name = ''
        else:
            name = ''

        overview_url = overview_elem['href'] if overview_elem else ''

        points = row.find('td', class_='best')
        if points:
            points = points.get_text(strip=True)
        else:
            points = ''

        diff_elem = row.find('span', class_=['rank-up', 'rank-down'])
        diff = diff_elem.get_text(strip=True) if diff_elem else ''
        if diff:
            diff = '+' + diff if int(diff) > 0 else diff

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

            # arrêter la boucle si on a déjà extrait les 10 premiers joueurs
            if len(leaderboard_players) == 10:
                break
