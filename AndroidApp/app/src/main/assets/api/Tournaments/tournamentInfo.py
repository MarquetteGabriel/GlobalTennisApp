import csv
import json
import os
import requests
from datetime import datetime


def getATPTournamentInfo(tournament_id):

    year = datetime.now().year

    url_overview = "https://www.atptour.com/en/-/tournaments/profile/" + str(tournament_id) + "/overview"
    url_pastChampions = "https://www.atptour.com/en/-/tournaments/" + str(tournament_id) + "/pastchampions"
    url_topseeds = "https://www.atptour.com/en/-/tournaments/" + str(tournament_id) + "/" + str(year) + "/topseeds"
    url_prize = "https://www.atptour.com/en/-/tournaments/" + str(tournament_id) + "/" + str(year) + "/prizeandpoints"


    payload_overview = ""
    headers_overview = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }
    payload_players = ""
    headers_players = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }
    payload_pastChampion = ""
    headers_pastChampion = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }
    payload_topseeds = ""
    headers_topseeds = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }
    payload_prize = ""
    headers_prize = {
        "cookie": "__cf_bm=oBsqxeb7NT8ath7aQUqwkC5ZfAsnGl56ygLvvP_bhvs-1711448380-1.0.1.1-.AkYRMam6CJ2cg49V830L7fm3k.s_6_AJ2_y81eTvNUAJI1SP.bHV2pu8HKbR3pxZiZsepcrRNDExKtOgwr1Tw",
        "User-Agent": "insomnia/8.6.1"
    }

    tournament_data = []

    response_overview = requests.request("GET", url_overview, data=payload_overview, headers=headers_overview)
    if(response_overview.status_code == 200) : 
        data = json.loads(response_overview.text)

        tournament_overview = {
            "Name": data['SponsorTitle'],
            "SingleDrawSize": data['SinglesDrawSize'],
            "DoubleDrawSize": data['DoublesDrawSize'],
            "Surface": data['Surface'],
            "Location": data['Location'],
            "IndoorOutdoor": data['InOutdoor'],
        }

        tournament_data.append(tournament_overview)

    response_pastChampions = requests.request("GET", url_pastChampions, data=payload_pastChampion, headers=headers_pastChampion)
    if(response_pastChampions.status_code == 200) :
        data = json.loads(response_pastChampions.text)

        pastChampions = []

        for champion in data['SinglesChampions']:
            champion_info = {
                "FirstName": champion['FirstName'],
                "LastName": champion['LastName'],
                "Year": champion['Year'],
            }
            pastChampions.append(champion_info)
        champions_dict = {
        "PastChampions": pastChampions
        }
        tournament_data[0].update(champions_dict)

    response_topseeds = requests.request("GET", url_topseeds, data=payload_topseeds, headers=headers_topseeds)
    if(response_topseeds.status_code == 200) :
        data = json.loads(response_topseeds.text)

        topseeds = []

        for seed in data['SinglePlayers']:
            seed_info = {
                "Seed": seed['SeedNumber'],
                "PlayerName": seed['FullName'],
            }
            topseeds.append(seed_info)
        seeds_dict = {
        "TopSeeds": topseeds
        }
        tournament_data[0].update(seeds_dict)

    response_prize = requests.request("GET", url_prize, data=payload_prize, headers=headers_prize)
    if(response_prize.status_code == 200) :
        data = json.loads(response_prize.text)

        prizeAndPoints = []

        for prize in data['Singles']:
            points = {
                "Round": prize['RoundName'],
                "Points": prize['Points'],
                "PrizeMoney": prize['Prize'],
            }
            prizeAndPoints.append(points)
        prize_and_points_dict = {
        "PrizeAndPoints": prizeAndPoints
        }
        tournament_data[0].update(prize_and_points_dict)

    json_tournament_info = json.dumps(tournament_data, indent=4)
    return json_tournament_info

def getMatchByYearByTournamentForATPSingles(tournament_name, year):

    folder_path = "./Archives/ATP/"

    all_data = []

    def filter_and_convert_to_json(csv_filepath, tournament_name):
        data = []
        with open(csv_filepath, 'r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if row['tourney_name'] == tournament_name:
                    data.append(row)
        return data

    for filename in os.listdir(folder_path):
        if filename.endswith(".csv") and filename.startswith("atp_matches_"):
            if year in filename:
                csv_filepath = os.path.join(folder_path, filename)
                brisbane_match_data = filter_and_convert_to_json(csv_filepath, tournament_name.capitalize())
                all_data.extend(brisbane_match_data)
                break

    return json.dumps(all_data, indent=4)

def getMatchByYearByTournamentForQualifSingles(tournament_name, year):

    folder_path = "./Archives/ATP/"

    all_data = []

    def filter_and_convert_to_json(csv_filepath, tournament_name):
        data = []
        with open(csv_filepath, 'r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if row['tourney_name'] == tournament_name:
                    data.append(row)
        return data

    for filename in os.listdir(folder_path):
        if filename.endswith(".csv") and filename.startswith("atp_matches_qual_chall_"):
            if year in filename:
                csv_filepath = os.path.join(folder_path, filename)
                brisbane_match_data = filter_and_convert_to_json(csv_filepath, tournament_name.capitalize())
                all_data.extend(brisbane_match_data)
                break

    return json.dumps(all_data, indent=4)

def getMatchByYearByTournamentForFutures(tournament_name, year):

    folder_path = "./Archives/ATP/"

    all_data = []

    def filter_and_convert_to_json(csv_filepath, tournament_name):
        data = []
        with open(csv_filepath, 'r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if row['tourney_name'] == tournament_name:
                    data.append(row)
        return data

    for filename in os.listdir(folder_path):
        if filename.endswith(".csv") and filename.startswith("atp_matches_futures_"):
            if year in filename:
                csv_filepath = os.path.join(folder_path, filename)
                brisbane_match_data = filter_and_convert_to_json(csv_filepath, tournament_name.capitalize())
                all_data.extend(brisbane_match_data)
                break

    return json.dumps(all_data, indent=4)

def getMatchByYearByTournamentForATPDoubles(tournament_name, year):

    folder_path = "./Archives/ATP/"

    all_data = []

    def filter_and_convert_to_json(csv_filepath, tournament_name):
        data = []
        with open(csv_filepath, 'r', newline='', encoding='utf-8') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                if row['tourney_name'] == tournament_name:
                    data.append(row)
        return data

    for filename in os.listdir(folder_path):
        if filename.endswith(".csv") and filename.startswith("atp_matches_doubles_"):
            if year in filename:
                csv_filepath = os.path.join(folder_path, filename)
                brisbane_match_data = filter_and_convert_to_json(csv_filepath, tournament_name.capitalize())
                all_data.extend(brisbane_match_data)
                break

    return json.dumps(all_data, indent=4)
