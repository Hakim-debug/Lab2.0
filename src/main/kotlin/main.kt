fun main(args: Array<String>) {
    val subscriptions= Subscriptions()
    val response=HttpRequest(subscriptions);
     subscriptions.setState(response.chatSentTo())
    subscriptions.setState(response.receivedFrom())
}