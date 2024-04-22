from flask import Blueprint, request, jsonify, json

from SchedulesAndResults.schedule import getResults

result_bp = Blueprint("reult_bp", __name__, url_prefix="/api/atp/")

@result_bp.route("/results/<date>")
def atp_results(date):
    results = json.loads(getResults(date))
    return jsonify(results)