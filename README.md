# Spring Boot-istio integration ![enter image description here](https://travis-ci.org/4sujittiwari/springboot-istio-integration.svg?branch=master)

This project will help you guys setting up istio Service Mesh and will also enable you to communicate between you Spring Boot microservices using kubernetes service discovery.
#
## Requirements

 - Java 8
 - Maven
 - Docker ([Installation Instruction](https://www.docker.com/products/docker-desktop))
 - Minikube ([Installation Instruction](https://kubernetes.io/docs/tasks/tools/install-minikube/))
 - Istio 
 
 ### Download Istio[](https://istio.io/docs/setup/getting-started/#download)

1.  Go to the  [Istio release](https://github.com/istio/istio/releases/tag/1.5.1)  page to download the installation file for your OS, or download and extract the latest release automatically (Linux or macOS):
    
    ```
    curl -L https://istio.io/downloadIstio | sh -
    ```
    
    The command above downloads the latest release (numerically) of Istio. To download a specific version, you can add a variable on the command line. For example to download Istio 1.4.3, you would run  `curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.4.3 sh -`
    
2.  Move to the Istio package directory. For example, if the package is  `istio-1.5.1`:
    
    ```
    cd istio-1.5.1
    ```
    
    The installation directory contains:
    
    -   Installation YAML files for Kubernetes in  `install/kubernetes`
    -   Sample applications in  `samples/`
    -   The  [`istioctl`](https://istio.io/docs/reference/commands/istioctl)  client binary in the  `bin/`  directory.
3.  Add the  `istioctl`  client to your path (Linux or macOS):
    
    ```
    export PATH=$PWD/bin:$PATH
    ```
    

### Install Istio[](https://istio.io/docs/setup/getting-started/#install)

1.  For this installation, we use the  `demo`  [configuration profile](https://istio.io/docs/setup/additional-setup/config-profiles/). It’s selected to have a good set of defaults for testing, but there are other profiles for production or performance testing.
    
    ```
    istioctl manifest apply --set profile=demo
    ```
    
2.  Add a namespace label to instruct Istio to automatically inject Envoy sidecar proxies when you deploy your application later:
    
    ```
    kubectl label namespace default istio-injection=enabled
    ```
 
 
### Building and Creating Docker Image for Spring boot application
		

    cd customer
	mvn clean package
	docker build -t example/customer .
	cd..
	cd preferences
	mvn clean package
	docker build -t example/preference . 

## Kubernetes Commands 
	kubectl config set-context $(kubectl config current-context) --namespace=tutorial
	
	kubectl apply -f <(istioctl kube-inject -f customer/kubernetes/Deployment.yml) -n tutorial
	
	kubectl create -f customer/kubernetes/Service.yml -n tutorial
	
	kubectl create -f customer/kubernetes/Gateway.yml -n tutorial
	
	kubectl get pods -w -n tutorial
	
	kubectl get svc istio-ingressgateway -n istio-system
	
	export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
	
	export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
	
	export INGRESS_HOST=$(minikube ip)



Run this command in a new terminal window to start a Minikube tunnel that sends traffic to your Istio Ingress Gateway:

	minikube tunnel

Export the gateway URL for communication

	export GATEWAY_URL=$INGRESS_HOST:$INGRESS_PORT
	echo $GATEWAY_URL
	curl $GATEWAY_URL/customer

For Istio Kiali Dashboard
	
	istioctl dashboard kiali
