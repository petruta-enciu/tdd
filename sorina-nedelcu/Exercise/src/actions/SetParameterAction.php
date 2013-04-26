<?php
require_once 'Action.php';
require_once '/ContextSingleton.php';
require_once '/util/NamespaceObject.php';
require_once '/util/Property.php';


/**
 * Created by JetBrains PhpStorm.
 * User: Sorina Nedelcu
 * Date: 4/22/13
 * Time: 3:59 PM
 * To change this template use File | Settings | File Templates.
 */
class SetParameterAction implements Action {

  private $context;  //ContextSingleton

  public function __construct() {
    $this->context = ContextSingleton::getInstance();
  }

  public function doAction($params) {
    $msg = "";
    if(count($params)<=2) {
      $msg = "Not enough parameters! Try again!\n";
    } else {
      //get current Namespace
      $currentNamespace = $this->context->getCurrentNamespace();
      $namespace = $this->context->getNamespaceObj($currentNamespace);
      $properties = $namespace->getProperties();


      $property = new Property($params[1], $params[2]);
      $properties[] = $property;

      $namespace->setProperties($properties);

      $this->context->addNamespace($currentNamespace,$namespace);
      $this->context->setCurrentNamespace($currentNamespace);


      for ($i=0; $i<count($properties); $i++) {
        $proprName = $properties[$i]->getName();
        $proprValue = $properties[$i]->getValue();
        $msg = $msg . "  " . $namespace->getName() . " : " . $proprName . "=" . $proprValue .  "\n";
      }
    }
  return $msg;
  }
}