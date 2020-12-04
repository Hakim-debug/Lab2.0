

class ErrorHandler:Exception(){
  public fun status400(): Exception {
      return Exception("400")
  }
    public fun status401(): Exception {
        return Exception("401")
    }
    public fun status500(): Exception {
        return Exception("500")
    }
}