from flask import Flask, request, jsonify
import json

from LiveScore.livedata import getLiveScore
from Tournaments.calendarATP import getCalendarATP
from SchedulesAndResults.schedule import getResults
from Leaderboard.official_leaderboard import getLeaderboardATPOfficial
from Leaderboard.race_leaderboard import getLeaderboardATPRace
from Leaderboard.live_leaderboard import getLeaderboardATPLive
import Tournaments.tournamentInfo as tournamentInfo

app = Flask(__name__)

# HTTP Methods
# GET : Request data from a specified resource
# POST : Send data to a server
# PUT : Update resource
# DELETE : Delete resource

""" Leaderboard """

@app.route("/api/atp/rankings/singles") # Maybe too long
def singles_ranking_all():
    atp_leaderboard = json.loads(getLeaderboardATPOfficial())
    return jsonify(atp_leaderboard)

@app.route("/api/atp/rankings/singles/race") # Maybe too long
def singles_ranking_race():
    atp_leaderboard_race = json.loads(getLeaderboardATPRace())
    return jsonify(atp_leaderboard_race)

@app.route("/api/atp/rankings/singles/live") # Maybe too long
def singles_ranking_live():
    atp_leaderboard_live = json.loads(getLeaderboardATPLive())
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

@app.route("/api/")
def home():
    return jsonify({"message": "Home"})

def main():
    app.run(debug=True, port=2607, use_reloader=False)

if __name__ == "__main__":
    main()