#Instruction
        
             is the Tic-tac-toe Web Service exercise. The prerequisite of this exercise are the UI/UX training. You are supposed to have already the UI/UX of
        Tic-tac-toe web app and presumably you passed the training of you UI/UX of course. On this excercise we are going to make a Controller which is part of the MVC framework
        since you already have a View component which is the UI of your Tic-tac-toe, But do take note that the Model part in this exercise databases are not requried yet, you are 
        going to save all the data on a flat files. This will allow your Tic-tac-toe web application to save the game info along with the player
        moves..
   
        Do the ff:
            1) Create a Webservice Application made from JAX-RS.
            2) Modify your Tic-tac-toe web app for every move by a player. call the webservice with the "/save" path on the Controller.
            3) Create a screen that lists all the previous games of the players using this path "/listGames/<playerId>" on your Controller.
                Make sure that the screen is accessible from a link labeled "History" from at least 1 page of your Tic-tac-toe web app
            4) Each retrieved game will be listed in a table on that screen.
            5) When a listed game is pressed, call this path "/getGame/<gameId>" on your Controller
            6) Using the retrieved information, display the move records in date ascending
            7) Optional:Recreate the game display and moves on the grid/tile. Update the screen movement after every 2 seconds
            until the game is finished. Add a button to allow to replay the animation (5points)
            
        
#Data Model

    #Game Data Storage
    your file name would be like this:
        <Gameid>.txt
    
    the content of the file:
        <gameid>,<playerid>,<symbol>,<location>,<datesave>
            
            
    #Player Data Storage
    your file name would be like this:
        <Playerid>.txt
        
    the content of the file:
        <gameid>
        <gameid>
        <gameid>
        <gameid>
    
        
        
#Webservices 

    #Save
    
    Description:
        Save the record
    
    Path: https://<ip>:port/rest/save
    Request Type: POST
    Request Headers:
        Content-Type: application/json
        Authorization: none
        
    Request Body(JSON type): 
    {
        "gameid":<Game id>,
        "symbol":<Symbol/Character used by the player>,
        "location":<Grid number of the move>,
        "playerid":<Id of the player>,
        "datesave":<Current timestamp record was saved>
    }
    
    Response:
        if status 200 (JSON):
        {
            msg: "Record saved"
        }
        
        if status 401(Text):
        "Record could not saved"
        
        if status 500 (Text):
        "The server ran into an unexpected exception"
        
    ********************************************************************************************************************
            
    #Get Player Games
    
    Description:
        Get records
        
    Path: https://ip:port/rest/listgames/{playerid}
    Request Type: GET
    Request Headers:
        Content-Type: application/json
        Authorization: none
    
    Request Body(JSON type): 
    {
        "playerid":<Id of the playe>,
    }
    
    Response:
        if status 200 (JSON):
        {
            "list”: [
             { “id”:”0001”},
             { “id”:”0002”}
            ],
             msg: “Records found”
        }
        
        if status 401(Text):
        "Record not found"
        
        if status 500 (Text):
        "The server ran into an unexpected exception"
        

    ********************************************************************************************************************
    
    #Get Game Details
    
    Description:
        Return the game details
    
    Path: https://ip:port/rest/getgame/{gameid}
    Request Type: POST
    Request Headers:
        Content-Type: application/json
        Authorization: none
    
    Request Body(JSON type): 
    {
        "playerid":<Id of the player>,
    }
    
    
    
    Response:
        if status 200 (JSON):
        {
             "list”: [
             { 
                   “gameid”:”0001”,
                   ”playerid”:”787asdfjhsdf”, 
                   “symbol”:”O”, 
                   “location”:”9”, 
                   “datesaved:” “2021-11-11 01:00:00”
             }, 
             { 
                   “gameid”:”0001”,
                   ”playerid”:”iudsfiousadfsdf”, 
                   “symbol”:”X”, 
                   “location”:”0”, 
                   “datesaved:” “2021-11-11 01:03:00”
             }
        ],
             msg: “Records found
        }
        
        if status 401(Text):
        "Record not found"
        
        if status 500 (Text):
        "The server ran into an unexpected exception"
        
    
    
        
    
    
        
    ********************************************************************************************************************
        
    Note:
    These are the BARE MINIMUM specifications you need to follow for this exercises. You may add a Controller
    in your webservice If and only if you have a creative additional feature you want to add on your tic tac toe web app you may come up with
    , But if one of the BARE MINIMUM specs aren't included in your web app are consider FAILED
    
    
    
    Good Luck Trainees!!
    
