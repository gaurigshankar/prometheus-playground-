# Prometehus Playground

Important links
1. https://www.innoq.com/en/blog/prometheus-counters/
2. https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/

### Spring Boot
We have a simple spring boot project. It has two important dependencies for prometheus playground
1. spring-boot-starter-actuator
2. micrometer-registry-prometheus

#### sprint boot actuator
 Read more in https://www.javatpoint.com/spring-boot-actuator. In simple words, this library provides few rest endpoints
 that helps us with data for system monitoring. These endpoints can be accessed via `http://<<server>>:<<port>>/<<contextROOT>>/actuator`

#### Micrometer  registry prometheus
Read more in https://micrometer.io/docs/registry/prometheus This will work hand in hand with actuator. Micrometer helps 
collecting metrics and then distributing them as per specifications of multiple metrics systems prometheus, etcs
When integrated with `sprint boot actuator` we now have an endpoint `http://<<server>>:<<port>>/<<contextROOT>>/actuator/prometheus`
This endpoint provides metrics of the app so that can be ingested into prometheus. 

### Prometheus Docker Container
We need prometehus DB to ingest data into. We can start a docker container to run prometehus. 
`docker run -p 9090:9090 -v $PWD/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus` can be used to start prometheus.
Need to provide prometheus.yml from the app to the container. Prometheus server starts and available in `localhost:9090` 
    1. http://localhost:9090/targets will show the metrics getting scraped by prometheus
    2. http://localhost:9090/graph will have way to view metrics available
This is sample
```yaml
global:
  scrape_interval: 10s

scrape_configs:
  - job_name: 'spring_micrometer'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ['host.docker.internal:8080']
```

### Grafana Docker Container
We can start Grafana docker container using `docker run -p 3000:3000 grafana/grafana`. Then visit http://localhost:3000 to view grafana dahsboard.
Login with admin/admin
Add data source as prometheus as shown here [](images/grafana-configure-prometheus-datasource.png)
https://stackabuse.com/monitoring-spring-boot-apps-with-micrometer-prometheus-and-grafana/ has info on how to configure grafana

