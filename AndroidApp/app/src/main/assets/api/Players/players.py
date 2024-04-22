import requests, json
from bs4 import BeautifulSoup

payload = ""
headers = {
    "cookie": "__cf_bm=.MZB9mEMeccKnWTYbMdt6w_fQcEKfQm_Y6YicIJR7MI-1711447446-1.0.1.1-0ObIP0BYlb6WoI9NGVg5HBGfIG_SZ50s6VOL09EoybKsLZBC.BqkZLISVtuZzcqiZ14HIj9BUa3FCiPPydUokw",
    "User-Agent": "insomnia/8.6.1"
}

url = "https://www.atptour.com/en/-/www/players/hero/"

url_rank = "https://www.atptour.com/en/-/www/rank/history/"

url_stats = "https://www.atptour.com/en/-/www/stats/"

url_sgl = "https://www.atptour.com/en/-/www/activity/sgl/"

url_dbl = "https://www.atptour.com/en/-/www/activity/dbl/"

def getPlayerATP(player_id):
    player_information = []
    player_information.append(getPlayerATPOverview(player_id))
    ranking_dict = {"ranking": getPlayerRankings(player_id)}
    player_information[0].update(ranking_dict)
    stats_dict = {"stats": getPlayerStatsCarreer(player_id)}
    player_information[0].update(stats_dict)
    sgl_dict = {"sglMatches": getPlayerStatsSingles(player_id)}
    player_information[0].update(sgl_dict)
    dbl_dict = {"dblMatches": getPlayerStatsDoubles(player_id)}
    player_information[0].update(dbl_dict)



    return json.dumps(player_information, indent=4) 

def getPlayerATPOverview(player_id):    
    response = requests.get(url + str(player_id) + "?", headers=headers)

    if(response.status_code == 200) : 
        data = json.loads(response.text)

        player_data = {
        "LastName": data["LastName"],
        "FirstName": data["FirstName"],
        "BirthCity": data["BirthCity"],
        "Residence": data["Residence"],
        "Coach": data["Coach"],
        "BirthDate": data["BirthDate"],
        "Age": data["Age"],
        "Nationality": data["Nationality"],
        "HeightFt": data["HeightFt"],
        "HeightCm": data["HeightCm"],
        "WeightLb": data["WeightLb"],
        "WeightKg": data["WeightKg"],
        "PlayHand": data["PlayHand"],
        "BackHand": data["BackHand"],
        "ProYear": data["ProYear"],
        "Active": data["Active"],
        "DblSpecialist": data["DblSpecialist"],
        "SglRank": data["SglRank"],
        "SglHiRank": data["SglHiRank"],
        "SglRankMove": data["SglRankMove"],
        "SglRankTie": data["SglRankTie"],
        "DblRank": data["DblRank"],
        "DblHiRank": data["DblHiRank"],
        "DblRankMove": data["DblRankMove"],
        "DblRankTie": data["DblRankTie"],
        "ScRelativeUrlPlayerProfile": data["ScRelativeUrlPlayerProfile"],
        "SglCareerWon": data["SglCareerWon"],
        "SglCareerLost": data["SglCareerLost"],
        "SglYtdWon": data["SglYtdWon"],
        "SglYtdLost": data["SglYtdLost"],
        "SglCareerTitles": data["SglCareerTitles"],
        "SglYtdTitles": data["SglYtdTitles"],
        "SglYtdPrizeFormatted": data["SglYtdPrizeFormatted"],
        "CareerPrizeFormatted": data["CareerPrizeFormatted"],
        "DblCareerWon": data["DblCareerWon"],
        "DblCareerLost": data["DblCareerLost"],
        "DblYtdWon": data["DblYtdWon"],
        "DblYtdLost": data["DblYtdLost"],
        "DblCareerTitles": data["DblCareerTitles"],
        "DblYtdTitles": data["DblYtdTitles"],
        "DblYtdPrizeFormatted": data["DblYtdPrizeFormatted"],
        "IsCarbonTrackerEnabled": data["IsCarbonTrackerEnabled"],
        "SocialLinks": data["SocialLinks"],
        "SglHiRankDate": data["SglHiRankDate"],
        "DblHiRankDate": data["DblHiRankDate"]
        }

    return player_data

def getPlayerRankings(player_id):
    response = requests.get(url_rank + str(player_id) + "?", headers=headers)

    if(response.status_code == 200) : 
        data = json.loads(response.text)

        player_rankings = {
            "FirstRankYear": data["FirstRankYear"],
            "LastRankYear": data["LastRankYear"],
            "RankingHistory": []
        }

        for ranking in data["History"]:
            ranking_data = {
                "RankDate": ranking["RankDate"],
                "SglRollRank": ranking["SglRollRank"],
                "SglRollTie": ranking["SglRollTie"],
                "SglRollPoints": ranking["SglRollPoints"],
                "SglRaceRank": ranking["SglRaceRank"],
                "SglRaceTie": ranking["SglRaceTie"],
                "SglRacePoints": ranking["SglRacePoints"],
                "DblRollRank": ranking["DblRollRank"],
                "DblRollTie": ranking["DblRollTie"],
                "DblRollPoints": ranking["DblRollPoints"],
            }
            player_rankings["RankingHistory"].append(ranking_data)

        return player_rankings
            
def getPlayerStatsCarreer(player_id):
    response = requests.get(url_stats + str(player_id) + "/all/all?", headers=headers)

    if(response.status_code == 200) :
        data = json.loads(response.text)

        stats_data = {
        "FirstStatYear": data["FirstStatYear"],
        "LastStatYear": data["LastStatYear"],
        "PlayerId": data["Stats"]["PlayerId"],
        "RankDate": data["Stats"]["RankDate"],
        "Category": data["Stats"]["Category"],
        "Surface": data["Stats"]["Surface"],
        "EventYear": data["Stats"]["EventYear"],
        "ServiceRecordStats": {
            "Aces": data["Stats"]["ServiceRecordStats"]["Aces"],
            "DoubleFaults": data["Stats"]["ServiceRecordStats"]["DoubleFaults"],
            "FirstServePercentage": data["Stats"]["ServiceRecordStats"]["FirstServePercentage"],
            "FirstServePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["FirstServePointsWonPercentage"],
            "SecondServePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["SecondServePointsWonPercentage"],
            "BreakPointsFaced": data["Stats"]["ServiceRecordStats"]["BreakPointsFaced"],
            "BreakPointsSavedPercentage": data["Stats"]["ServiceRecordStats"]["BreakPointsSavedPercentage"],
            "ServiceGamesPlayed": data["Stats"]["ServiceRecordStats"]["ServiceGamesPlayed"],
            "ServiceGamesWonPercentage": data["Stats"]["ServiceRecordStats"]["ServiceGamesWonPercentage"],
            "ServicePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["ServicePointsWonPercentage"],},
        "ReturnRecordStats": {
            "FirstServeReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["FirstServeReturnPointsWonPercentage"],
            "SecondServeReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["SecondServeReturnPointsWonPercentage"],
            "BreakPointsOpportunities": data["Stats"]["ReturnRecordStats"]["BreakPointsOpportunities"],
            "BreakPointsConvertedPercentage": data["Stats"]["ReturnRecordStats"]["BreakPointsConvertedPercentage"],
            "ReturnGamesPlayed": data["Stats"]["ReturnRecordStats"]["ReturnGamesPlayed"],
            "ReturnGamesWonPercentage": data["Stats"]["ReturnRecordStats"]["ReturnGamesWonPercentage"],
            "ReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["ReturnPointsWonPercentage"],
            "TotalPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["TotalPointsWonPercentage"],
            },
        }

        return stats_data

def getPlayerStatsSingles(player_id):
    response = requests.get(url_sgl + str(player_id) + "?", headers=headers)

    if (response.status_code == 200) :
        data = json.loads(response.text)

        tournament_data = {
        "PlayerId": data["PlayerId"],
        "DblSpecialist": data["DblSpecialist"],
        "Won": data["Won"],
        "Lost": data["Lost"],
        "Titles": data["Titles"],
        "Prize": data["Prize"],
        "ActivityYearsList": data["ActivityYearsList"],
        "Tournaments": [],
        }

        for year_data in data["Activity"]:
            for tournament in year_data["Tournaments"]:
                tournament_info = {
                    "EventId": tournament["EventId"],
                    "TournamentUrl": tournament["TournamentUrl"],
                    "EventName": tournament["EventName"],
                    "ScDisplayName": tournament["ScDisplayName"],
                    "EventDisplayName": tournament["EventDisplayName"],
                    "Location": tournament["Location"],
                    "EventDate": tournament["EventDate"],
                    "PlayEndDate": tournament["PlayEndDate"],
                    "EventType": tournament["EventType"],
                    "InOutdoor": tournament["InOutdoor"],
                    "Surface": tournament["Surface"],
                    "SglDrawSize": tournament["SglDrawSize"],
                    "DblDrawSize": tournament["DblDrawSize"],
                    "Points": tournament["Points"],
                    "PlayerRank": tournament["PlayerRank"],
                    "Prize": tournament["Prize"],
                    "CurrSymbol": tournament["CurrSymbol"],
                    "PrizeUsd": tournament["PrizeUsd"],
                    "TotFinclCommit": tournament["TotFinclCommit"],
                    "TotPrizeMoney": tournament["TotPrizeMoney"],
                    "HiRound": tournament["HiRound"],
                    "Matches": [],
                }

                for match in tournament["Matches"]:
                    match_info = {
                        "Round": match["Round"],
                        "MatchDate": match["MatchDate"],
                        "MatchId": match["MatchId"],
                        "WinLoss": match["WinLoss"],
                        "PartnerId": match["PartnerId"],
                        "PartnerFirstName": match["PartnerFirstName"],
                        "PartnerLastName": match["PartnerLastName"],
                        "PartnerCmsItemName": match["PartnerCmsItemName"],
                        "PartnerRank": match["PartnerRank"],
                        "PartnerNatlId": match["PartnerNatlId"],
                        "OpponentId": match["OpponentId"],
                        "OpponentLastName": match["OpponentLastName"],
                        "OpponentFirstName": match.get("OpponentLastName", ""),
                        "OpponentPartnerRank": match["OpponentPartnerRank"],
                        "OpponentRank": match["OpponentRank"],
                        "Set1Player": match.get("Set1Player", ""),
                        "Set1Opponent": match.get("Set1Opponent", ""),
                        "Set2Player": match.get("Set2Player", ""),
                        "Set2Opponent": match.get("Set2Opponent", ""),
                        "Set2Tie": match.get("Set2Tie"),
                        "Set3Player": match.get("Set3Player"),
                        "Set3Opponent": match.get("Set3Opponent"),
                        "HasStats": match["HasStats"],
                        "MatchStatsUrl": match["MatchStatsUrl"],
                        "IsBye": match["IsBye"],
                        "IsWinLossCountable": match["IsWinLossCountable"],
                        "IsTitleCountable": match["IsTitleCountable"],
                        "OpponentCmsItemName": match["OpponentCmsItemName"],
                        "OpponentPartnerCmsItemName": match["OpponentPartnerCmsItemName"],
                    }
                    tournament_info["Matches"].append(match_info)

                tournament_data["Tournaments"].append(tournament_info)

        return tournament_data

def getPlayerStatsDoubles(player_id):
    response = requests.get(url_dbl + str(player_id) + "?", headers=headers)
    
    if (response.status_code == 200) :
        data = json.loads(response.text)

        tournament_data = {
        "PlayerId": data["PlayerId"],
        "DblSpecialist": data["DblSpecialist"],
        "Won": data["Won"],
        "Lost": data["Lost"],
        "Titles": data["Titles"],
        "Prize": data["Prize"],
        "ActivityYearsList": data["ActivityYearsList"],
        "Tournaments": [],
        }

        for year_data in data["Activity"]:
            for tournament in year_data["Tournaments"]:
                tournament_info = {
                    "EventId": tournament["EventId"],
                    "TournamentUrl": tournament["TournamentUrl"],
                    "EventName": tournament["EventName"],
                    "ScDisplayName": tournament["ScDisplayName"],
                    "EventDisplayName": tournament["EventDisplayName"],
                    "Location": tournament["Location"],
                    "EventDate": tournament["EventDate"],
                    "PlayEndDate": tournament["PlayEndDate"],
                    "EventType": tournament["EventType"],
                    "InOutdoor": tournament["InOutdoor"],
                    "Surface": tournament["Surface"],
                    "SglDrawSize": tournament["SglDrawSize"],
                    "DblDrawSize": tournament["DblDrawSize"],
                    "Points": tournament["Points"],
                    "PlayerRank": tournament["PlayerRank"],
                    "Prize": tournament["Prize"],
                    "CurrSymbol": tournament["CurrSymbol"],
                    "PrizeUsd": tournament["PrizeUsd"],
                    "TotFinclCommit": tournament["TotFinclCommit"],
                    "TotPrizeMoney": tournament["TotPrizeMoney"],
                    "HiRound": tournament["HiRound"],
                    "Matches": [],
                }

                for match in tournament["Matches"]:
                    match_info = {
                        "Round": match["Round"],
                        "MatchDate": match["MatchDate"],
                        "MatchId": match["MatchId"],
                        "WinLoss": match["WinLoss"],
                        "PartnerId": match["PartnerId"],
                        "PartnerFirstName": match["PartnerFirstName"],
                        "PartnerLastName": match["PartnerLastName"],
                        "PartnerCmsItemName": match["PartnerCmsItemName"],
                        "PartnerRank": match["PartnerRank"],
                        "PartnerNatlId": match["PartnerNatlId"],
                        "OpponentId": match["OpponentId"],
                        "OpponentLastName": match["OpponentLastName"],
                        "OpponentFirstName": match.get("OpponentLastName", ""),
                        "OpponentPartnerRank": match["OpponentPartnerRank"],
                        "OpponentRank": match["OpponentRank"],
                        "Set1Player": match.get("Set1Player", ""),
                        "Set1Opponent": match.get("Set1Opponent", ""),
                        "Set2Player": match.get("Set2Player", ""),
                        "Set2Opponent": match.get("Set2Opponent", ""),
                        "Set2Tie": match.get("Set2Tie"),
                        "Set3Player": match.get("Set3Player"),
                        "Set3Opponent": match.get("Set3Opponent"),
                        "HasStats": match["HasStats"],
                        "MatchStatsUrl": match["MatchStatsUrl"],
                        "IsBye": match["IsBye"],
                        "IsWinLossCountable": match["IsWinLossCountable"],
                        "IsTitleCountable": match["IsTitleCountable"],
                        "OpponentCmsItemName": match["OpponentCmsItemName"],
                        "OpponentPartnerCmsItemName": match["OpponentPartnerCmsItemName"],
                    }
                    tournament_info["Matches"].append(match_info)

                tournament_data["Tournaments"].append(tournament_info)

        return tournament_data

def getPlayerStats(player_id, year, ground):
    response = requests.get(url_stats + str(player_id) + "/" + str(year) + "/" + str(ground) + "?", headers=headers)

    if(response.status_code == 200) :
        data = json.loads(response.text)

        stats_data = {
        "FirstStatYear": data["FirstStatYear"],
        "LastStatYear": data["LastStatYear"],
        "PlayerId": data["Stats"]["PlayerId"],
        "RankDate": data["Stats"]["RankDate"],
        "Category": data["Stats"]["Category"],
        "Surface": data["Stats"]["Surface"],
        "EventYear": data["Stats"]["EventYear"],
        "ServiceRecordStats": {
            "Aces": data["Stats"]["ServiceRecordStats"]["Aces"],
            "DoubleFaults": data["Stats"]["ServiceRecordStats"]["DoubleFaults"],
            "FirstServePercentage": data["Stats"]["ServiceRecordStats"]["FirstServePercentage"],
            "FirstServePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["FirstServePointsWonPercentage"],
            "SecondServePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["SecondServePointsWonPercentage"],
            "BreakPointsFaced": data["Stats"]["ServiceRecordStats"]["BreakPointsFaced"],
            "BreakPointsSavedPercentage": data["Stats"]["ServiceRecordStats"]["BreakPointsSavedPercentage"],
            "ServiceGamesPlayed": data["Stats"]["ServiceRecordStats"]["ServiceGamesPlayed"],
            "ServiceGamesWonPercentage": data["Stats"]["ServiceRecordStats"]["ServiceGamesWonPercentage"],
            "ServicePointsWonPercentage": data["Stats"]["ServiceRecordStats"]["ServicePointsWonPercentage"],},
        "ReturnRecordStats": {
            "FirstServeReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["FirstServeReturnPointsWonPercentage"],
            "SecondServeReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["SecondServeReturnPointsWonPercentage"],
            "BreakPointsOpportunities": data["Stats"]["ReturnRecordStats"]["BreakPointsOpportunities"],
            "BreakPointsConvertedPercentage": data["Stats"]["ReturnRecordStats"]["BreakPointsConvertedPercentage"],
            "ReturnGamesPlayed": data["Stats"]["ReturnRecordStats"]["ReturnGamesPlayed"],
            "ReturnGamesWonPercentage": data["Stats"]["ReturnRecordStats"]["ReturnGamesWonPercentage"],
            "ReturnPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["ReturnPointsWonPercentage"],
            "TotalPointsWonPercentage": data["Stats"]["ReturnRecordStats"]["TotalPointsWonPercentage"],
            },
        }

        return json.dumps(stats_data, indent=4)

