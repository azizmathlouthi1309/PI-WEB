<?php

namespace ClassBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ClassBundle:Default:index.html.twig');
    }

    public function indexClassAction()
    {

        $em = $this->getDoctrine()->getManager();

        $cls = $em->getRepository('ClassBundle:Classe')->findAll();
        return $this->render('/default/indexClass.html.twig',array(
            'classes' => $cls
        ));
    }
    }

