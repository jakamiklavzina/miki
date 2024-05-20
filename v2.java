<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Roulette</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
    }
    
    .container {
        width: 300px;
        margin: 0 auto;
        text-align: center;
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    
    button {
        padding: 10px 20px;
        margin-top: 10px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    
    #wheel {
        width: 200px;
        height: 200px;
        background-color: #f0f0f0;
        border-radius: 50%;
        border: 5px solid #333;
        position: relative;
        animation: spin 5s infinite linear;
    }

    .marker {
        width: 0;
        height: 0;
        border-left: 10px solid transparent;
        border-right: 10px solid transparent;
        border-bottom: 20px solid red;
        position: absolute;
        top: -20px;
        left: 95px;
    }

    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
</style>
</head>
<body>

<div class="container">
    <h1>Welcome to Roulette!</h1>
    <p>You start with $1000.</p>
    <p>Place your bet on:</p>
    <ol>
        <li>A specific number (payout 35:1)</li>
        <li>Red or Black (payout 1:1)</li>
        <li>Odd or Even (payout 1:1)</li>
        <li>High (19-36) or Low (1-18) (payout 1:1)</li>
    </ol>
    <p>Your current balance is: <span id="balance">$1000</span></p>
    <input type="number" id="betAmount" placeholder="Enter your bet amount">
    <br><br>
    <button onclick="playRoulette()">Spin</button>
    <br><br>
    <div id="wheel">
        <div class="marker"></div>
    </div>
</div>

<script>
    function spinWheel() {
        var randomDegree = Math.floor(Math.random() * 360) + 1;
        document.getElementById('wheel').style.transform = 'rotate(' + randomDegree + 'deg)';
    }

    function playRoulette() {
        var money = 1000;
        var betAmount = parseInt(document.getElementById('betAmount').value);
        if (isNaN(betAmount) || betAmount <= 0) {
            alert("Invalid bet amount!");
            return;
        }

        var spinResult = Math.floor(Math.random() * 37); // Roulette wheel numbers from 0 to 36
        var colorResult = (spinResult === 0) ? -1 : (spinResult % 2 === 0) ? 0 : 1;

        var message = "The wheel spun and the result is: " + spinResult + "\n";
        if (colorResult === 0) {
            message += "The color is: Black\n";
        } else if (colorResult === 1) {
            message += "The color is: Red\n";
        }

        var win = false;
        var betType = parseInt(prompt("Choose your bet type (1-4):\n1. Specific number\n2. Red or Black\n3. Odd or Even\n4. High or Low"));
        switch (betType) {
            case 1:
                var betNumber = parseInt(prompt("Enter your bet number (0-36):"));
                if (isNaN(betNumber) || betNumber < 0 || betNumber > 36) {
                    alert("Invalid bet number!");
                    return;
                }
                if (betNumber === spinResult) {
                    win = true;
                    money += betAmount * 35;
                }
                break;
            case 2:
                var betColor = parseInt(prompt("Enter your bet color (0 for Black, 1 for Red):"));
                if (isNaN(betColor) || (betColor !== 0 && betColor !== 1)) {
                    alert("Invalid bet color!");
                    return;
                }
                if (betColor === colorResult) {
                    win = true;
                    money += betAmount;
                }
                break;
            case 3:
                var betOddEven = parseInt(prompt("Enter your bet (0 for Even, 1 for Odd):"));
                if (isNaN(betOddEven) || (betOddEven !== 0 && betOddEven !== 1)) {
                    alert("Invalid bet!");
                    return;
                }
                if ((spinResult !== 0) && ((spinResult % 2) === betOddEven)) {
                    win = true;
                    money += betAmount;
                }
                break;
            case 4:
                var betHighLow = parseInt(prompt("Enter your bet (0 for Low 1-18, 1 for High 19-36):"));
                if (isNaN(betHighLow) || (betHighLow !== 0 && betHighLow !== 1)) {
                    alert("Invalid bet!");
                    return;
                }
                if ((spinResult !== 0) && (((spinResult >= 1 && spinResult <= 18) && betHighLow === 0) || ((spinResult >= 19 && spinResult <= 36) && betHighLow === 1))) {
                    win = true;
                    money += betAmount;
                }
                break;
            default:
                alert("Invalid bet type!");
                return;
        }

        message += win ? "Congratulations! You won!\n" : "You lost.\n";
        message += "Your current balance is: $" + money;
        alert(message);
        document.getElementById('balance').textContent = "$" + money;

        // Call the function to spin the wheel
        spinWheel();
    }
</script>

</body>
</html>
