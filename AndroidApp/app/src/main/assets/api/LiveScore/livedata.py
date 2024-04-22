import requests, json

url_live = "https://www.atptour.com/en/-/www/LiveMatches/"
headers = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }

def getLiveScore(year, tournament_id):
    response = requests.get(url_live + "/" + str(year) + "/" + str(tournament_id), headers=headers)
    
    if(response.status_code == 200) :
        data = json.loads(response.text)

        live_data = {
            "Eventyear": data['Eventyear'],
            "EventId": data['EventId'],
            "Eventyear": data['Eventyear'],
            "EventId": data['EventId'],
            "EventCountry": data['EventCountry'],
            "EventLocation": data['EventLocation'],
            "EventCity": data['EventCity'],
            "EventStartDate": data['EventStartDate'],
            "EventEndDate": data['EventEndDate'],
            "EventCurrentDayNumber": data['EventCurrentDayNumber'],
            "LiveMatches": [],
            }

        for match in data['LiveMatches']:
            match_info = {
                "TeamTieResults": match['TeamTieResults'],
                "MatchId": match['MatchId'],
                "IsDoubles": match['IsDoubles'],
                "RoundName": match['RoundName'],
                "CourtName": match['CourtName'],
                "MatchTimeTotal": match['MatchTimeTotal'],
                "ExtendedMessage": match['ExtendedMessage'],
                "MatchStatus": match['MatchStatus'],
                "ServerTeam": match['ServerTeam'],
                "WinningPlayerId": match['WinningPlayerId'],
                "PlayerTeam": {
                    "GameScore": match["PlayerTeam"]['GameScore'],
                    "Partner": {
                        "PlayerCountryName": match["PlayerTeam"]['Partner']['PlayerCountryName'],
                        "PlayerFirstName": match["PlayerTeam"]['Partner']['PlayerFirstName'],
                        "PlayerId": match["PlayerTeam"]['Partner']['PlayerId'],
                        "PlayerLastName": match["PlayerTeam"]['Partner']['PlayerLastName'],
                        },
                    "PartnerProfileUrl": match["PlayerTeam"].get('PartnerProfileUrl', ""),
                    "Player": {
                        "PlayerCountryName": match["PlayerTeam"]['Player']['PlayerCountryName'],
                        "PlayerFirstName": match["PlayerTeam"]['Player']['PlayerFirstName'],
                        "PlayerId": match["PlayerTeam"]['Player']['PlayerId'],
                        "PlayerLastName": match["PlayerTeam"]['Player']['PlayerLastName'],
                        },
                    "PlayerProfileUrl": match["PlayerTeam"].get('PlayerProfileUrl', ""),
                    "Seed": match["PlayerTeam"]['Seed'],
                    "SetScores": match["PlayerTeam"]['SetScores'],
                    },           
                "OpponentTeam": {
                    "GameScore": match["OpponentTeam"]['GameScore'],
                    "Partner": {
                        "PlayerCountryName": match["OpponentTeam"]['Partner']['PlayerCountryName'],
                        "PlayerFirstName": match["OpponentTeam"]['Partner']['PlayerFirstName'],
                        "PlayerId": match["OpponentTeam"]['Partner']['PlayerId'],
                        "PlayerLastName": match["OpponentTeam"]['Partner']['PlayerLastName'],
                        },
                    "PartnerProfileUrl": match["OpponentTeam"].get('PartnerProfileUrl', ""),
                    "Player": {
                        "PlayerCountryName": match["OpponentTeam"]['Player']['PlayerCountryName'],
                        "PlayerFirstName": match["OpponentTeam"]['Player']['PlayerFirstName'],
                        "PlayerId": match["OpponentTeam"]['Player']['PlayerId'],
                        "PlayerLastName": match["OpponentTeam"]['Player']['PlayerLastName'],
                        },
                    "PlayerProfileUrl": match["OpponentTeam"].get('PlayerProfileUrl', ""),
                    "Seed": match["OpponentTeam"]['Seed'],
                    "SetScores": match["OpponentTeam"]['SetScores'],
                    },
                "HeadToHeadUrl": match.get('HeadToHeadUrl', ""),
                "MatchStatsUrl": match.get('MatchStatsUrl', ""),
                "MatchStateReasonMessage": match.get('MatchStateReasonMessage', ""),
                "UmpireFirstName": match.get('UmpireFirstName', ""),
                "UmpireLastName": match.get('UmpireLastName', ""),
                "Type": match.get('Type', ""),
                "CourtId": match.get('CourtId', ""),
                }

            live_data['LiveMatches'].append(match_info)
        
        return json.dumps(live_data, indent=4)