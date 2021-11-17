package main
import "fmt"

func main(){
	PeacePeace(3.20, 3.20, 5, 5)
	APeaceBWar(3.20, 3.20, 5, 5)
}


func PeacePeace(priceA float32, priceB float32, customersA float32, customersB float32){
	companyAPrice := priceA
	companyBPrice := priceB
	companyACustomers := customersA
	companyBCustomers := customersB
	companyAProfit := float32(0)
	companyBProfit := float32(0)

	fmt.Printf("Company A AND Company B are peaceful\n")
	fmt.Printf("-------------------------------------\n")

	/*
	If gas company B lowers the price to lower than gas company A's,
	customers will go to gas company B, so gas company A needs to lower it more
	so that more customers will go to gas company A
	*/
	for i:=0; i < 5; i++{
		fmt.Printf("Day %d\n", i+1)
		fmt.Printf("----------------------------\n")
		if(companyAPrice < companyBPrice){
			companyBPrice = companyAPrice - 0.1
		}
		if(companyBPrice < companyAPrice){
			companyAPrice = companyBPrice - 0.1
		}

		if(companyAPrice < companyBPrice){
			companyACustomers += 1
			companyBCustomers -= 1
		}
		if(companyBPrice < companyAPrice){
			companyBCustomers += 1
			companyACustomers -= 1
		}
		companyAProfit += companyAPrice * companyACustomers
		companyBProfit += companyBPrice * companyBCustomers
		fmt.Printf("Company A gas price: $%.2f\n", companyAPrice)
		fmt.Printf("Company B gas price: $%.2f\n", companyBPrice)
		fmt.Printf("Company A customers: %.0f\n", companyACustomers)
		fmt.Printf("Company B customers: %.0f\n", companyBCustomers)
		fmt.Printf("Company A profit: $%.2f\n", companyAProfit)
		fmt.Printf("Company B profit: $%.2f\n", companyBProfit)
		fmt.Printf("----------------------------\n")
	}

	fmt.Printf("-------------------------------------\n")
	fmt.Printf("Final Company A gas price: $%.2f\n", companyAPrice)
	fmt.Printf("Final Company B gas price: $%.2f\n", companyBPrice)
	fmt.Printf("Final Company A customers: %.0fd\n", companyACustomers)
	fmt.Printf("Final Company B customers: %.0f\n", companyBCustomers)
	fmt.Printf("Final Company A profit: $%.2f\n", companyAProfit)
	fmt.Printf("Final Company B profit: $%.2f\n", companyBProfit)
	fmt.Printf("-------------------------------------\n")
	/*
	However, because this is a peace-peace agreement,
	neither company lowers the price, and therefore make the most money possible
	*/

}


//TODO: Parametarize this so that it can be APeaceBWar or AWarBPeace (ends in same result, just for different company)
func APeaceBWar(priceA float32, priceB float32, customersA int, customersB int){

	companyAPrice := priceA
	companyBPrice := priceB
	companyACustomers := customersA
	companyBCustomers := customersB


	fmt.Printf("\n\n")
	fmt.Printf("Company A is peaceful, Company B lowers prices\n")
	fmt.Printf("-------------------------------------\n")


	for i:=0; i < 5; i++{
		fmt.Printf("Day %d\n", i+1)
		fmt.Printf("----------------------------\n")
		if(companyBPrice > 3.10){
			companyBPrice -= 0.1
		}else{
			companyBPrice -= 0.01
		}
		if(companyBPrice < companyAPrice){
			companyACustomers -= 1
			companyBCustomers += 1
		}
		fmt.Printf("Company A gas price: $%.2f\n", companyAPrice)
		fmt.Printf("Company B gas price: $%.2f\n", companyBPrice)
		fmt.Printf("Company A customers: %d\n", companyACustomers)
		fmt.Printf("Company B customers: %d\n", companyBCustomers)
		fmt.Printf("----------------------------\n")
	}
	fmt.Printf("-------------------------------------\n")
	fmt.Printf("Final Company A gas price: $%.2f\n", companyAPrice)
	fmt.Printf("Final Company B gas price: $%.2f\n", companyBPrice)
	fmt.Printf("Final Company A customers: %d\n", companyACustomers)
	fmt.Printf("Final Company B customers: %d\n", companyBCustomers)
	fmt.Printf("-------------------------------------\n")


}
