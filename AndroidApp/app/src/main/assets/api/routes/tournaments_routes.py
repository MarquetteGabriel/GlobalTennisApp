from flask import Blueprint, request, jsonify, json

from Tournaments.calendarATP import getCalendarATP
from Tournaments.draws import getDraws
import Tournaments.tournamentInfo as tournamentInfo

tournament_bp = Blueprint("tournament_bp", __name__, url_prefix="/api/atp/tournaments")

@tournament_bp.route("/calendar")
def tournaments():
    tournaments = json.loads(getCalendarATP())
    return jsonify(tournaments)

@tournament_bp.route("/<tournament_id>")
def atp_tournaments(tournament_id):
    atp_tournaments = json.loads(tournamentInfo.getATPTournamentInfo(tournament_id))
    return jsonify(atp_tournaments)

@tournament_bp.route("/<tournament>/<tournament_id>/draws")
def atp_tournaments_draws(tournament, tournament_id):
    atp_tournaments = json.loads(getDraws(tournament, tournament_id))
    return jsonify(atp_tournaments)

@tournament_bp.route("/<tournament_name>/<year>/results")
def atp_tournaments_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForATPSingles(tournament_name, year))
    return jsonify(atp_tournaments)

@tournament_bp.route("/<tournament_name>/<year>/qualif-results")
def atp_tournaments_qualif_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForQualifSingles(tournament_name, year))
    return jsonify(atp_tournaments)

@tournament_bp.route("/<tournament_name>/<year>/futures-results")
def atp_tournaments_futures_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForFutures(tournament_name, year))
    return jsonify(atp_tournaments)

@tournament_bp.route("/<tournament_name>/<year>/doubles-results")
def atp_tournaments_doubles_results(tournament_name, year):
    atp_tournaments = json.loads(tournamentInfo.getMatchByYearByTournamentForATPDoubles(tournament_name, year))
    return jsonify(atp_tournaments)