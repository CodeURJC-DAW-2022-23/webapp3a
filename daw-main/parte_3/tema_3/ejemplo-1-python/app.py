import os
from flask import Flask, redirect, url_for, request, render_template
from pymongo import MongoClient

app = Flask(__name__)

dbHost = "localhost" if os.getenv("MONGODB_HOST") is None else os.getenv("MONGODB_HOST")
dbPort = "27017" if os.getenv("MONGODB_PORT") is None else os.getenv("MONGODB_PORT")

client = MongoClient(dbHost, int(dbPort))
db = client.postsdb

print ("Database Configurations:")
print ("\t- MONGODB_HOST:", dbHost)
print ("\t- MONGODB_PORT:", dbPort)

@app.route('/')
def posts():

    _items = db.postsdb.find()
    items = [item for item in _items]

    return render_template('index.html', items=items)


@app.route('/new', methods=['POST'])
def new():

    item_doc = {
        'name': request.form['name'],
        'description': request.form['description']
    }
    db.postsdb.insert_one(item_doc)

    return redirect(url_for('posts'))

if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)