import requests, json

url = "https://api.sofascore.com/api/v1/sport/tennis/scheduled-events/"

def getResults(date):
    payload = ""
    headers = {"User-Agent": "insomnia/8.6.1"}

    response = requests.request("GET", url + date, data=payload, headers=headers)

    if(response.status_code == 200):
        data = json.loads(response.text)

        matches_info = []

        for match in data['events']:
            match_info = {
                "id": match['id'],
                "tournament": match['tournament']['name'],
                "tournament_category": match['tournament']['category']['name'],
                "home_player": match['homeTeam']['name'],
                "home_flag": match['homeTeam']['country'],
                "away_player": match['awayTeam']['name'],
                "away_flag": match['awayTeam']['country'],
                "firstToServe": match.get('firstToServe', ''),
                "home_scores": {period: match.get('homeScore', {}).get(f'period{period}', '') for period in range(1, 6)},
                "away_scores": {period: match.get('awayScore', {}).get(f'period{period}', '') for period in range(1, 6)},
                "home_tiebreak_scores": {period: match.get('homeScore', {}).get(f'period{period}TieBreak', '') for period in range(1, 6)},
                "away_tiebreak_scores": {period: match.get('awayScore', {}).get(f'period{period}TieBreak', '') for period in range(1, 6)},
                "home_point": match.get('homeScore', {}).get('point', ''),
                "away_point": match.get('awayScore', {}).get('point', ''),
                "startTimestamp": match['startTimestamp'],
                "status": match['status']['type']
            }
            matches_info.append(match_info)

    json_matches_info = json.dumps(matches_info, indent=4)
    return json_matches_info