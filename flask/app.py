import os
import subprocess
from flask import Flask, render_template, request, redirect, url_for
from flask_wtf import FlaskForm
from wtforms import FileField, SubmitField
from werkzeug.utils import secure_filename
from flask_socketio import SocketIO, emit

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret_key'

socketio = SocketIO(app)

class UploadForm(FlaskForm):
    file = FileField('Upload File')
    submit = SubmitField('Upload')

@app.route('/', methods=['GET', 'POST'])
def upload():
    form = UploadForm()
    if form.validate_on_submit():
        f = form.file.data
        filename = secure_filename(f.filename)
        f.save(os.path.join(app.root_path, 'uploads', filename))
        subprocess.Popen(['argo', 'submit', '-n', 'argo', '--watch', os.path.join(app.root_path, 'uploads', filename)])
        return redirect('https://localhost:2746/')
    return render_template('upload.html', form=form)

@app.route('/success')
def success():
    return 'File uploaded successfully!'

if __name__ == '__main__':
    app.run(debug=False)
