This is a java project for value portfolio



Step 1: Clone the code

Step 2: Build the maven project by  run package.bat

Step 3: Run the jar by runapp-dev.bat

Step 4: Start Price Feed by sending below "Turn on price feed" curl command

Step 5: Upload csv trade file by sending file path in relative path through "Uplodate Price Data csv file" command

The return file path is available in return Json.

You can see the latest price in DB:select * from stock_price

The NAV changes for latest portfolio is printed in command line once a csv trade file is uploaded.



#Turn on price feed
curl -H "Content-Type: application/json" -X POST -d '{"requestPara":"prieOn" }'  http://localhost:10000/PriceCSV/startPrice

#Turn off price feed
curl -H "Content-Type: application/json" -X POST -d '{"requestPara":"priceOff" }'  http://localhost:10000/PriceCSV/closePrice

#Uplodate Price Data csv file
curl  -H "Content-Type: application/json" -X POST -d '{"filePath":"\\trade.csv" }' http://localhost:10000/Valuation/getLatestPrice

#DB 
URL:http://localhost:10000/console
User name and password is configurable in application.yml file


