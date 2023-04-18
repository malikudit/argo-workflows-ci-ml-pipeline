import pandas as pd
from pandas.core.groupby.generic import generate_property
from sklearn.model_selection import train_test_split
import argparse


def generate_data():
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--location", help="url of file to be processed", required=True)
    parser.add_argument('-t', '--test_size', type=float,
                        default=0.2, help='split size for the test data')
    args = parser.parse_args()
    data = pd.read_csv(args.location)

    x = data.iloc[:, :-1]
    y = data.iloc[:, -1]

    y = y.map({'Iris-setosa': 1, 'Iris-virginica': 2, 'Iris-versicolor': 3})

    x_train, x_test, y_train, y_test = train_test_split(
        x, y, stratify=y, test_size=args.test_size)

    x_train.to_csv('/tmp/x_train.csv', index=False)
    x_test.to_csv('/tmp/x_test.csv', index=False)
    y_train.to_csv('/tmp/y_train.csv', index=False)
    y_test.to_csv('/tmp/y_test.csv', index=False)

    # Todo: use a logger!
    print("process completed successfully...")


if __name__ == "__main__":
    generate_data()