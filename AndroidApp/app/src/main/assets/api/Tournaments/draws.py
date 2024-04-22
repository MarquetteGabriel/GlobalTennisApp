import requests, json, copy
from bs4 import BeautifulSoup



headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3'}


def getDraws(tournament, tournamentId):

    url = "https://www.atptour.com/en/scores/current/" + str(tournament) + "/" + str(tournamentId) + "/draws"

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        html = response.text

        soup = BeautifulSoup(html, 'html.parser')
        # Find all stats items
        stats_items = soup.find_all('div', class_='stats-item')

        player_names = []

        for item in stats_items:
            player_info = item.find('div', class_='player-info')
            player_name = player_info.find('div', class_='name').text.strip()
            scores = item.find('div', class_='scores')

            winner_div = item.find('div', class_='winner')
            is_winner = True if winner_div else False

            if(player_name not in player_names or player_name == 'Bye') and player_name != "":
                player_names.append(player_name)

        round = len(player_names)
        id = 1

        draw_matches = []
        header = {"round": round}
        draw_matches.append(header)

        for i in range(0, len(player_names), 2):
            draw_match = {
                "home_player": player_names[i],
                "away_player": player_names[i+1],
                "draw_match_id": id,
            }

            draw_matches.append(draw_match)
            id += 1

        json_draw_info = json.dumps(draw_matches, indent=4)
        return json_draw_info