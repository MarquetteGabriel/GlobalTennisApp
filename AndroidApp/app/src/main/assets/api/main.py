from flask import Flask, request, jsonify
import json

from LiveScore.livedata import getLiveScore
from Tournaments.calendarATP import getCalendarATP
from SchedulesAndResults.schedule import getResults
import Tournaments.tournamentInfo as tournamentInfo

app = Flask(__name__)

# HTTP Methods
# GET : Request data from a specified resource
# POST : Send data to a server
# PUT : Update resource
# DELETE : Delete resource


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