<br/>
<p align="center">
  <a href="https://github.com/a.sosa/hotel-buenas-noches-api">
    <img src="https://imgix.bustle.com/scary-mommy/2019/09/GettyImages-146582583-min-1.jpg?w=740&h=740&fit=crop&crop=faces&auto=format%2Ccompress" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Buenas Noches Hotel API</h3>

  <p align="center">
    An SPRING API and a Postgres DB that can run locally using docker and it's K8s ready.
    <br/>
    <br/>
  </p>
</p>

![Downloads](https://img.shields.io/github/downloads/a.sosa/hotel-buenas-noches-api/total) ![Forks](https://img.shields.io/github/forks/a.sosa/hotel-buenas-noches-api?style=social) ![Stargazers](https://img.shields.io/github/stars/a.sosa/hotel-buenas-noches-api?style=social) ![Issues](https://img.shields.io/github/issues/a.sosa/hotel-buenas-noches-api) ![License](https://img.shields.io/github/license/a.sosa/hotel-buenas-noches-api)

## Table Of Contents

* [About the Project](#about-the-project)
* [Built With](#built-with)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
* [Usage](#usage)
* [Authors](#authors)

## About The Project

![Screen Shot](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.dailymail.co.uk%2Fi%2Fpix%2F2014%2F05%2F10%2Farticle-2624972-1DB18D9300000578-618_634x561.jpg&f=1&nofb=1)

This is a SPRING API that runs in a Docker container and connects to a Postgres DB that also runs in a container.

The API uses of a lot of stuff that I completely forgot what it uses, but it can make CRUD operations in the database.

The postgres db when deployed, runs an script that dumps data that creates the tables and relations between tables so it's ready to use.

The api when deployed to kubernetes can scale horizontally if enough requests are made. To do so, there's a K6 script within the project to perform the stress in order the scale the API horizontally.

## Built With

Docker
Kubernetes
JPA
Lombok
Spring Boot
Gradle
PostgreSQL

## Getting Started

TOOLS REQUIRED TO RUN ALL THE STUFF IN THIS PROJECT:
- k6
- Docker
- Kubernetes
- Gradle

if something is missing email me at:
a.sosa@globant.com

### Prerequisites

You need to install Docker and a Kubernetes Node ready. I recommend installing Docker Desktop since it can configure everything for you.

Docker Desktop:
https://www.docker.com/products/docker-desktop/

The tools below are also needed, but depending on the OS, the process is quite different. So find a way to install each tool:

- Kubectl
- Gradle
- k6

### Installation

Running the project locally in docker.

1. Run docker-compose script to build the images and deploy the containers.

```sh
docker-compose -f ./docker-compose-localDev.yml up --build
```
In case that you want to deploy the images in a K8s node.

1. Build the jar

```sh
gradle clean build --no-daemon -x test
```

2. Build the docker image for the API and the postgres DB.

```sh
docker-compose -f ./docker-compose-k8s.yml build
```

4. Create the kubernetes namespace for the api

```sh
 kubectl create namespace buenas-noches-api
```

5. Apply the postgres-deployment k8s manifest

```sh
 kubectl apply -f postgres-deployment.yml
```

4. Apply the buenas-noches-api k8s manifest

```sh
kubectl apply -f ./buenas-noches-deployment.yml
```

## Usage

Check the status of the pods using

```sh
kubectl get pods --all--namespaces
```

You will get the pods running in namespace buenas-noches-api:
```sh
$ kubectl get pods --all-namespaces
NAMESPACE              NAME                                        READY   STATUS    RESTARTS         AGE
buenas-noches-api      buenas-noches-api-599bd66958-5q7gc          1/1     Running   0                2m29s
buenas-noches-api      postgres-6d99b9fcbd-xn476                   1/1     Running   0                2m29s
kube-system            coredns-78fcd69978-dfpdx                    1/1     Running   1 (6m21s ago)    15m
kube-system            coredns-78fcd69978-kqjcj                    1/1     Running   1 (6m21s ago)    15m
kube-system            etcd-docker-desktop                         1/1     Running   8 (6m21s ago)    15m
kube-system            kube-apiserver-docker-desktop               1/1     Running   9 (6m21s ago)    15m
kube-system            kube-controller-manager-docker-desktop      1/1     Running   8 (6m21s ago)    15m
kube-system            kube-proxy-5ggc4                            1/1     Running   1 (6m21s ago)    15m
kube-system            kube-scheduler-docker-desktop               1/1     Running   12 (6m21s ago)   15m
kube-system            metrics-server-8bb87844c-ws8r9              1/1     Running   0                4m12s
kube-system            storage-provisioner                         1/1     Running   2 (5m42s ago)    15m
kube-system            vpnkit-controller                           1/1     Running   2 (5m41s ago)    15m
kubernetes-dashboard   dashboard-metrics-scraper-c45b7869d-4fh7s   1/1     Running   0                4m2s
kubernetes-dashboard   kubernetes-dashboard-764b4dd7-drbs6         1/1     Running   0                4m2s
```

Once kubernetes performs the health checks, test the API making a GET request in endpoint:

http://localhost:8080/api/v1/hotel

You should be getting a 200 response with a body response similar to this:
```sh
[
    {
        "id": "3e0d31e8-4972-11ec-9cbe-047d7bb283ba",
        "name": "TEST HOTEL",
        "description": "THIS IS A TEST HOTEL",
        "stars": 3,
        "rooms": [
            {
                "id": "5997bd66-4972-11ec-b12d-047d7bb283ba",
                "name": "TEST COMMON ROOM NAME",
                "description": "THIS IS A TEST DESCRIPTION FOR TEST ROOM NAME",
                "floor": 1,
                "maxGuests": 2,
                "roomType": {
                    "name": "Common"
                }
            },
            {
                "id": "6fe3d1ae-4972-11ec-a2b4-047d7bb283ba",
                "name": "TEST SUITE ROOM NAME",
                "description": "THIS IS A TEST DESCRIPTION FOR A SUITE",
                "floor": 1,
                "maxGuests": 2,
                "roomType": {
                    "name": "Suite"
                }
            },
            {
                "id": "7cf31670-4972-11ec-8f54-047d7bb283ba",
                "name": "TEST VIP ROOM NAME",
                "description": "THIS IS A TEST DESCRIPTION FOR A VIP ROOM",
                "floor": 1,
                "maxGuests": 2,
                "roomType": {
                    "name": "VIP"
                }
            },
            {
                "id": "859f750c-4972-11ec-874b-047d7bb283ba",
                "name": "TEST PENTHOUSE ROOM NAME",
                "description": "THIS IS A TEST DESCRIPTION FOR A PENTHOUSE",
                "floor": 1,
                "maxGuests": 2,
                "roomType": {
                    "name": "Penthouse"
                }
            }
        ]
    },
    {
        "id": "8e926562-51fa-11ec-9c4b-047d7bb283ba",
        "name": "VIP HOTEL",
        "description": "SECOND HOTEL TEST",
        "stars": 5,
        "rooms": [
            {
                "id": "c66d026c-51fa-11ec-972d-047d7bb283ba",
                "name": "TEST PENTHOUSE ROOM NAME IN SECOND HOTEL",
                "description": "THIS IS A TEST DESCRIPTION FOR A PENTHOUSE",
                "floor": 1,
                "maxGuests": 2,
                "roomType": {
                    "name": "Penthouse"
                }
            }
        ]
    },
    {
        "id": "6ff1c136-cbb3-4025-83a7-d02d8a62684c",
        "name": "Hotel Prueba",
        "description": "Este es una pruebea lol",
        "stars": 5,
        "rooms": null
    }
]
```

To check the logs of the API pod use:

```sh
kubectl logs buenas-noches-api-599bd66958-5q7gc --namespace=buenas-noches-api
```

replace 'buenas-noches-api-599bd66958-5q7gc' with a pod name in your pod cluster.

## Run the kubernetes dashboard

If you want to load the Kubernetes dashboard

1. Build the manifest:
```bash
  kubectl apply -f ./k8s/kubernetes-dashboard/
```
2. Run the dashboard:

```bash
  kubectl proxy
```

3. Then create a token in order to access it:

```bash
  kubectl -n kubernetes-dashboard get secret $(kubectl -n kubernetes-dashboard get sa/admin-user -o jsonpath="{.secrets[0].name}") -o go-template="{{.data.token | base64decode}}"
```

you will get something like this:

```bash
  eyJhbGciOiJSUzI1NiIsImtpZCI6Im5PWkVvYV82dmIwT3M3UmpZVHplc3NCUzc5ZlZyOWhZbXRGTTFwbWV6bkEifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLTJsMjdmIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiIyYWUyNWZmMi01ZmQ4LTRlMmYtYjllZS1lOWIwMDE4NTIzZTUiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6YWRtaW4tdXNlciJ9.d1YDYkTbeQ5iyLSl7pSWAjriLtUbWPxbGGg3UrozW6agFeRo2f6TC688v9wdFXEXIvcgJaduSOA0m3sp8iChmvQR890qWoBCB-rSx6SbWNi3IfRMkP4IqFZKCfsCWIgCM8eBQdiBcCV6Axv8f1DhZVVlpjU2Bmp3tMCmGWxin8FHgTVJ-x_1qtgVfZUPPj9VjTveU9v_U6r-jHpGUqvVGoJlgyAy-1dC2RqCePOOe4fkP1yVZNyOjFibL61eHTUnUteMCV9l_SpYaZhpq-IrPDWvsEQxs86PwS901mqX90e20uL35X9tIMVXi40fjii4ZZq3BVMVxaQ-tAo5AzmGow
```

4. Open the following site in your web browser:

http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

Choose the Token option and paste the token you generated in step 3.

5. Once the dashboard is loaded, filter by namespace = buenas-noches-api and you will see the Api and database pods status

You can hit the demo-api here:

http://localhost:8080

## IMPORTANT
If you want to enable Horizontal Scaling in your local Kubernetes cluster. You need
to deploy de Metrics API because is not deployed by default when you run kubernetes locally.

The metrics Api is used to fetch CPU and Memory status to kubernetes nodes.

to deploy the API run this command:

```bash
  kubectl apply -f ./k8s/metrics-server/
```

and validate all is good running:
```bash
  kubectl top node
```
```bash
  kubectl top pod -A
```
You should get CPU and Memory status usage from every pod deployed.

## Horizontal Scale

If you want to scale horizontally. We need to hit the API several times at once.

K6 is needed so find a way to install it :P

Once k6 is installed run the following command to stress the API.

```bash
  k6 run -u 1000 -i 40000 --batch 100 ./http-request-testing-with-k6/test.js
```
and see how the pods auto-scale by themselves.

### Creating A Pull Request

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Authors

* **Alan Sosa Mejia** - *Test Automation Engineer* - [Alan Sosa Mejia](https://github.globant.com/a-sosa/) - *Not in a project yet :C*