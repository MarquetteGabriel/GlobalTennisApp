import requests, json

url = "https://api.sofascore.com/api/v1/sport/tennis/events/live"

def getLiveScore():
    payload = ""
    headers = {
        "Accept": "*/*",
        "Accept-Language": "fr-FR,fr;q=0.7",
        "Cache-Control": "max-age=0",
        "Origin": "https://www.sofascore.com",
        "Referer": "https://www.sofascore.com/",
        "Sec-Ch-Ua-Mobile": "?0",
        "Sec-Fetch-Dest": "empty",
        "Sec-Fetch-Mode": "cors",
        "Sec-Fetch-Site": "same-site",
        "Sec-Gpc": "1",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"
    }

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        data = json.loads(response.text)

        matches_info = []

        for match in data['events']:
            match_info = {
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
                "startTimestamp": match['startTimestamp']
            }
            matches_info.append(match_info)

    json_matches_info = json.dumps(matches_info, indent=4)
    return json_matches_info