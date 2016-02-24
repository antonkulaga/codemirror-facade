package org.denigma.preview

import akka.actor.{ActorSystem, _}

/**
 * For running as kernel
 */
object Main extends App
{
  implicit val system = ActorSystem()

  sys.addShutdownHook(system.terminate())
  var main: ActorRef = system.actorOf(Props[MainActor])
  main ! AppMessages.Start(5554)


}
