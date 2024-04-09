import json
from flask import Flask, jsonify

import Leaderboard.live_leaderboard as liveLeaderboard
import Leaderboard.official_leaderboard as offLeaderboard
import Tournaments.tournamentInfo as tournamentInfo
from Leaderboard.race_leaderboard import getLeaderboardATPRace
from LiveScore.livedata import getLiveScore
from SchedulesAndResults.schedule import getResults
from Tournaments.calendarATP import getCalendarATP

app = Flask(__name__)

# HTTP Methods
# GET : Request data from a specified resource
# POST : Send data to a server
# PUT : Update resource
# DELETE : Delete resource

""" Leaderboard """

""" Official """

@app.route("/api/atp/rankings/singles") # Maybe too long
def singles_ranking_all():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTopAll())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/0-100")
def singles_ranking_top100():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop100())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/101-200")
def singles_ranking_top200():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop200())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/201-300")
def singles_ranking_top300():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop300())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/301-400")
def singles_ranking_top400():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop400())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/401-500")
def singles_ranking_top500():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop500())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/501-600")
def singles_ranking_top600():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop600())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/601-700")
def singles_ranking_top700():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop700())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/701-800")
def singles_ranking_top800():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop800())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/801-900")
def singles_ranking_top900():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop900())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/901-1000")
def singles_ranking_top1000():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1000())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1001-1100")
def singles_ranking_top1100():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1100())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1101-1200")
def singles_ranking_top1200():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1200())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1201-1300")
def singles_ranking_top1300():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1300())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1301-1400")
def singles_ranking_top1400():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1400())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1401-1500")
def singles_ranking_top1500():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1500())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/1501-5000")
def singles_ranking_top5000():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop5000())
    return jsonify(atp_leaderboard)

"""" Race """

@app.route("/api/atp/rankings/singles/race") # Maybe too long
def singles_ranking_race():
    atp_leaderboard_race = json.loads(getLeaderboardATPRace())
    return jsonify(atp_leaderboard_race)

""" Live """

@app.route("/api/atp/rankings/singles/live") # Maybe too long
def singles_ranking_live():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLive())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/0-100")
def singles_ranking_live_top100():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop100())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/101-200")
def singles_ranking_live_top200():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop200())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/201-300")
def singles_ranking_live_top300():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop300())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/301-400")
def singles_ranking_live_top400():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop400())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/401-500")
def singles_ranking_live_top500():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop500())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/501-600")
def singles_ranking_live_top600():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop600())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/601-700")
def singles_ranking_live_top700():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop700())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/701-800")
def singles_ranking_live_top800():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop800())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/801-900")
def singles_ranking_live_top900():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop900())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/901-1000")
def singles_ranking_live_top1000():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1000())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1001-1100")
def singles_ranking_live_top1100():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1100())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1101-1200")
def singles_ranking_live_top1200():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1200())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1201-1300")
def singles_ranking_live_top1300():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1300())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1301-1400")
def singles_ranking_live_top1400():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1400())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1401-1500")
def singles_ranking_live_top1500():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1500())
    return jsonify(atp_leaderboard_live)

@app.route("/api/atp/rankings/singles/live/1501-5000")
def singles_ranking_live_top5000():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop5000())
    return jsonify(atp_leaderboard_live)



""" Tournaments """

@app.route("/api/atp/tournaments")
def tournaments():
    tournaments = json.loads(getCalendarATP())
    return jsonify(tournaments)

@app.route("/api/atp/<tournament_id>")
def atp_tournaments(tournament_id):
    atp_tournaments = json.loads(tournamentInfo.getATPTournamentInfo(tournament_id))
    return jsonify(atp_tournaments)

@app.route("/api/atp/<tournament_name>/<year>/results")
def atp_tournaments_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForATPSingles(tournament_name, year))
    return jsonify(atp_tournaments)

@app.route("/api/atp/<tournament_name>/<year>/qualif-results")
def atp_tournaments_qualif_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForQualifSingles(tournament_name, year))
    return jsonify(atp_tournaments)

@app.route("/api/atp/<tournament_name>/<year>/futures-results")
def atp_tournaments_futures_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForFutures(tournament_name, year))
    return jsonify(atp_tournaments)

@app.route("/api/atp/<tournament_name>/<year>/doubles-results")
def atp_tournaments_doubles_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForATPDoubles(tournament_name, year))
    return jsonify(atp_tournaments)

""" Live Score """

@app.route("/api/live-score")
def get_live_scores():
    live_scores = json.loads(getLiveScore())
    return jsonify(live_scores)

""" Results """

@app.route("/api/results/<date>")
def atp_results(date):
    results = json.loads(getResults(date))
    return jsonify(results)


""" Main """

@app.route("/api/atp/")
def home():
    return jsonify({"message": "Home"})

def main():
    app.run(host='127.0.0.1', port=2607)

if __name__ == "__main__":
    main()