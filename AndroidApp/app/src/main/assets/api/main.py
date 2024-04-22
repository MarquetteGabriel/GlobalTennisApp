import json
from flask import Flask, jsonify

from routes.tournaments_routes import tournament_bp
from routes.leaderboard_routes import leaderboard_bp
from routes.players_routes import players_bp
from routes.results_routes import result_bp
from routes.liveScore_routes import livescore_bp


app = Flask(__name__)
app.register_blueprint(tournament_bp)
app.register_blueprint(leaderboard_bp)
app.register_blueprint(players_bp)
app.register_blueprint(livescore_bp)
app.register_blueprint(result_bp)

# HTTP Methods
# GET : Request data from a specified resource
# POST : Send data to a server
# PUT : Update resource
# DELETE : Delete resource

""" Main """

@app.route("/api/atp/")
def home():
    return jsonify({"message": "Home"})

def main():
    app.run(host='127.0.0.1', port=2607)

if __name__ == "__main__":
    main()