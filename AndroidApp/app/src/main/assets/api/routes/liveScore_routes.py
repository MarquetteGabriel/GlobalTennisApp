from flask import Blueprint, request, jsonify, json

from LiveScore.livedata import getLiveScore

livescore_bp = Blueprint("livescore_bp", __name__, url_prefix="/api/atp/")

@livescore_bp.route("/live-score")
def get_live_scores():
    live_scores = json.loads(getLiveScore(2024, 1536))
    return jsonify(live_scores)

