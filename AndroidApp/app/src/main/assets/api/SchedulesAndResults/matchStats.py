import requests, json

url = "https://api.sofascore.com/api/v1/event/"
id = "12228533"

payload = ""
headers = {"User-Agent": "insomnia/8.6.1"}

response = requests.request("GET", url + id + "/statistics", data=payload, headers=headers)

if (response.status_code == 200) :
    data = json.loads(response.text)

    matches_stats = []

    statistics = data["statistics"][0]["groups"][0]["statisticsItems"]

for group in data["statistics"][0]["groups"]:
    # Parcourir les éléments de statistique dans chaque groupe
    for stat in group["statisticsItems"]:
        # Créer un dictionnaire pour stocker les données de la statistique
        stat_dict = {
            "groupName": group["groupName"],
            "name": stat["name"],
            "home": stat["home"],
            "away": stat["away"],
            "homeValue": stat["homeValue"],
            "awayValue": stat["awayValue"],
            "homeTotal": stat.get("homeTotal", None),
            "awayTotal": stat.get("awayTotal", None)
        }
        # Ajouter le dictionnaire à la liste
        matches_stats.append(stat_dict)

    json_matches_info = json.dumps(matches_stats, indent=4)
print(json_matches_info)
