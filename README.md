
Prometheus:
```sh
sudo docker run -it --network host -d --name=prometheus -p 9090:9090 -v /home/lidarunium/Desktop/AbdullaBot/web/config/prometheus.yaml:/etc/prometheus/prometheus.yaml prom/prometheus --config.file=/etc/prometheus/prometheus.yaml
```
Grafana:
```sh
sudo docker run -it --network host -d --name=grafana -p 3000:3000 grafana/grafana-enterprise
```