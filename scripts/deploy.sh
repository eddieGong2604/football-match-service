if [[ -z $1 ]]; then
  echo """
HOW TO RUN:

  ./builddocker.sh <service_name>

Example:

  ./builddocker.sh football-match-service
  """
  exit 1
fi

service_name=$1
cd ..
mvn clean install
docker build --build-arg service_name=${service_name} -t ${service_name}:latest .
service_name=${service_name} docker-compose up --remove-orphans