import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score
import joblib

# the template spec injects data into the container from the previous task in our DAG
# data then available in whatever path we specify here -> /data/*.csv

# if I run this container without it being part of a workflow, fails
# because the image would not have data/*.csv in it. that data is passed into this container as an artifact 
# by the workflow, during the workflow execution by argo

x_train = pd.read_csv('/tmp/x_train.csv')
x_test = pd.read_csv('/tmp/x_test.csv')
y_train = pd.read_csv('/tmp/y_train.csv')
y_test = pd.read_csv('/tmp/y_test.csv')

model = DecisionTreeClassifier()

model.fit(x_train, y_train)

y_pred = model.predict(x_test)

score = accuracy_score(y_pred=y_pred, y_true=y_test)

# save model test performance
with open('/tmp/score.txt', 'w') as f:
    print('Model Score:', score, file=f)

# save model to disk
filename = '/tmp/finalized_model.sav'
joblib.dump(model, filename)


# outputs 2 artifacts: test score of our model, and the pickled model object