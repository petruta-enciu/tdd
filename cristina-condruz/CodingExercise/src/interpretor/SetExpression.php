<?php
/**
 * Created by JetBrains PhpStorm.
 * User: Cristina Condruz
 * Date: 4/19/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */
require_once('CommandExpression.php');
require_once('/utils/NamespaceProperty.php');
require_once('/singleton/MySingleton.php');

class SetExpression implements CommandExpression{
   private $arguments;
   private $msg;
   public function __construct($arguments){
       $this->arguments = $arguments;
   }
   public function setArguments($arguments){
         $this->arguments = $arguments;
   }
   public function getArguments(){
           return $this->arguments;
   }
  public function interpret(){
      //command should be set {parameter_name} {parameter_value}
      $currentSession = MySingleton::getInstance();
      $currentNamespace = $currentSession->getCurrentNamespace();
      $arrArguments = explode(" ", $this->getArguments());
      if(count($arrArguments) == 2){
        $currentProperty = new NamespaceProperty($arrArguments[0],$arrArguments[1]);
        $currentNamespace->addProperties($currentProperty);
        //$nmsProperties = $currentNamespace->getProperties();
        //print_r($nmsProperties);
        $this->msg = $currentNamespace->getName()." : ". $currentProperty->getName() ." = ". $currentProperty->getValue();
      }else{
        $this->msg = "Parameters incorrect. The command should be set {parameter_name} {parameter_value}.";
      }
      return $this->msg;
    }
}
