gameId=$(curl -H "Content-Type: application/json" -X POST  http://localhost:8080/games/ | awk -F'[:}]' '{print $(NF-1)}')

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "9" }' http://localhost:8080/games/$gameId/rolls
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "1" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "4" }' http://localhost:8080/games/$gameId/rolls 
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "6" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "9" }' http://localhost:8080/games/$gameId/rolls
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "1" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "3" }' http://localhost:8080/games/$gameId/rolls 
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "1" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls
curl -H "Content-Type: application/json"  -X POST -d '{ "pinsHit": "10" }' http://localhost:8080/games/$gameId/rolls

curl http://localhost:8080/scores/$gameId/
