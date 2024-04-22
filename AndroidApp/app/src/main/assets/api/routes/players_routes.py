from flask import Blueprint, request, jsonify, json

from Players.players import getPlayerATP, getPlayerStats

players_bp = Blueprint("players_bp", __name__, url_prefix="/api/atp/players")

@players_bp.route("/<player_id>")
def atp_players(player_id):
    player = json.loads(getPlayerATP(player_id))
    return jsonify(player)

@players_bp.route("/<player_id>/stats/<year>/<surface>")
def atp_players_stats(player_id, year, surface):
    player = json.loads(getPlayerStats(player_id, year, surface))
    return jsonify(player)