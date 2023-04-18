This setup guide is based on using minikube to run a Kubernetes cluster locally.
Requirements:
- minikube
- Argo CLI

1. Start a local single-node cluster:
$ minikube start

2. Create a new namespace for the workflow engine:
$ kubectl create ns argo

3. Install Argo Workflow and required components:
$ kubectl apply -n argo -f https://raw.githubusercontent.com/argoproj/argo-workflows/stable/manifests/quick-start-postgres.yaml

4. Access the namespace with a port-forward:
$ kubectl -n argo port-forward deployment/argo-server 2746:2746

5. Ensure that all required pods, services, and deployments are up and running in a new terminal. This may take a couple of minutes.
$ kubectl get all -n argo

6. You can now access the Argo UI at http://localhost:2746

7. To submit an example workflow, cd into its directory and use the following command:
$ argo submit -n argo --watch EXAMPLE_WORKFLOW.yml
