from flask import Flask, request, jsonify
import json

app = Flask(__name__)

# HTTP Methods
# GET : Request data from a specified resource
# POST : Send data to a server
# PUT : Update resource
# DELETE : Delete resource





""" Main """

@app.route("/api/")
def home():
    return jsonify({"message": "Home"})

def main():
    app.run(debug=True, port=2607, use_reloader=False)

if __name__ == "__main__":
    main()