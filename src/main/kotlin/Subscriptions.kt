import com.example.whocall.Observer

class Subscriptions {
    var observer =ArrayList<Observer>()
    var stateFul = ""
     fun subscribe( observer: Observer){
      this.observer.add(observer)
    }

    fun setState(stateFul: String){
       this.stateFul=stateFul
        notifyChange()
    }

     fun getState():String{

        return this.stateFul
    }
    private fun notifyChange(){
        for (observer: Observer in observer){
            observer.update();
        }
    }
}