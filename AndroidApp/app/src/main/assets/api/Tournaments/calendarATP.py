import requests,json

url = "https://www.atptour.com/en/-/tournaments/calendar/tour"

def getCalendarATP():
    payload = ""
    headers = {
        "cookie": "__cf_bm=.MZB9mEMeccKnWTYbMdt6w_fQcEKfQm_Y6YicIJR7MI-1711447446-1.0.1.1-0ObIP0BYlb6WoI9NGVg5HBGfIG_SZ50s6VOL09EoybKsLZBC.BqkZLISVtuZzcqiZ14HIj9BUa3FCiPPydUokw",
        "User-Agent": "insomnia/8.6.1"
    }

    response = requests.request("GET", url, data=payload, headers=headers)
    if(response.status_code == 200) : 
        data = json.loads(response.text)

        tournamentsATP = []

        # Parcourir les données et extraire les informations requises
        for tournament_date in data["TournamentDates"]:
            for tournament in tournament_date["Tournaments"]:
                tournament_data = {
                    #"Id": tournament["Id"],
                    "Name": tournament["Name"],
                    "FormattedDate": tournament["FormattedDate"],
                    #"Location": tournament["Location"],
                    #"Type": tournament["Type"],
                    #"OverviewUrl": tournament["TournamentOverviewUrl"],
                    #"url_tournament": tournament['TicketsUrl'],
                }
                tournamentsATP.append(tournament_data)

    # Convertir la liste de dictionnaires en JSON
    tournament_info_json = json.dumps(tournamentsATP, indent=4)
    return tournament_info_json