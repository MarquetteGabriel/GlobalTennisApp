from flask import Blueprint, request, jsonify, json

import Leaderboard.live_leaderboard as liveLeaderboard
import Leaderboard.official_leaderboard as offLeaderboard
from Leaderboard.race_leaderboard import getLeaderboardATPRace

leaderboard_bp = Blueprint("leaderboard_bp", __name__, url_prefix="/api/atp/rankings")

""" Official """

@leaderboard_bp.route("/singles") # Maybe too long
def singles_ranking_all():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTopAll())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/0-100")
def singles_ranking_top100():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop100())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/101-200")
def singles_ranking_top200():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop200())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/201-300")
def singles_ranking_top300():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop300())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/301-400")
def singles_ranking_top400():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop400())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/401-500")
def singles_ranking_top500():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop500())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/501-600")
def singles_ranking_top600():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop600())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/601-700")
def singles_ranking_top700():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop700())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/701-800")
def singles_ranking_top800():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop800())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/801-900")
def singles_ranking_top900():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop900())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/901-1000")
def singles_ranking_top1000():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1000())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1001-1100")
def singles_ranking_top1100():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1100())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1101-1200")
def singles_ranking_top1200():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1200())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1201-1300")
def singles_ranking_top1300():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1300())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1301-1400")
def singles_ranking_top1400():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1400())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1401-1500")
def singles_ranking_top1500():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop1500())
    return jsonify(atp_leaderboard)

@leaderboard_bp.route("/singles/1501-5000")
def singles_ranking_top5000():
    atp_leaderboard = json.loads(offLeaderboard.getLeaderboardATPOfficialTop5000())
    return jsonify(atp_leaderboard)

"""" Race """

@leaderboard_bp.route("/singles/race") # Maybe too long
def singles_ranking_race():
    atp_leaderboard_race = json.loads(getLeaderboardATPRace())
    return jsonify(atp_leaderboard_race)

""" Live """

@leaderboard_bp.route("/singles/live") # Maybe too long
def singles_ranking_live():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLive())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/0-100")
def singles_ranking_live_top100():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop100())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/101-200")
def singles_ranking_live_top200():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop200())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/201-300")
def singles_ranking_live_top300():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop300())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/301-400")
def singles_ranking_live_top400():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop400())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/401-500")
def singles_ranking_live_top500():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop500())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/501-600")
def singles_ranking_live_top600():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop600())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/601-700")
def singles_ranking_live_top700():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop700())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/701-800")
def singles_ranking_live_top800():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop800())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/801-900")
def singles_ranking_live_top900():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop900())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/901-1000")
def singles_ranking_live_top1000():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1000())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1001-1100")
def singles_ranking_live_top1100():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1100())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1101-1200")
def singles_ranking_live_top1200():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1200())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1201-1300")
def singles_ranking_live_top1300():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1300())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1301-1400")
def singles_ranking_live_top1400():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1400())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1401-1500")
def singles_ranking_live_top1500():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop1500())
    return jsonify(atp_leaderboard_live)

@leaderboard_bp.route("/singles/live/1501-5000")
def singles_ranking_live_top5000():
    atp_leaderboard_live = json.loads(liveLeaderboard.getLeaderboardATPLiveTop5000())
    return jsonify(atp_leaderboard_live)