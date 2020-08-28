import random

def main():
    list = ["rock", "paper", "scissors"]
    user_input = input("Rock, Paper or Scissors? ")
    enemy = list[random.randint(0, 2)];
    print("Player 1's choice: " + user_input)
    print("Computer's choice: " + enemy)
    if(user_input.lower() == "rock"):
        if enemy == "rock":
            print("Tie!")
        elif enemy == "scissors":
            print("Player 1 wins")
        else:
            print("Computer Wins")
    elif(user_input.lower() == "paper"):
        if enemy == "paper":
            print("Tie!")
        elif enemy == "rock":
            print("Player 1 wins")
        else:
            print("Computer wins")
    else:
        if enemy == "scissors":
            print("Tie!")
        elif enemy == "paper":
            print("Player 1 wins")
        else:
            print("Computer wins")
main()
