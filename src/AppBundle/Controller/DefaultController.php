<?php

namespace AppBundle\Controller;

use http\Client\Curl\User;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use TransportBundle\Entity\Driver;
use TransportBundle\Entity\Traffectation;
use TransportBundle\Entity\Vehicule;

class DefaultController extends Controller
{
    public function indexAction(Request $request)
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        if ($this->isGranted('ROLE_ADMIN') == true) {
            $em = $this->getDoctrine()->getManager();
            $vehicules = $em->getRepository(Vehicule::class)->findAll();
            $affectations = $em->getRepository(Traffectation::class)->findAll();
            $drivers = $em->getRepository(Driver::class)->findAll();
            $nb_veh = 0;
            $nb_driv = 0;
            foreach ($vehicules as $veh) {
                if ($veh->getStatus() == 0) {
                    $nb_veh++;
                }
            }
            foreach ($drivers as $driver)
            {
                foreach ($affectations as $affect)
                {
                    if($affect->getDriver()->getId()==$driver->getId())
                    {
                        $nb_driv++;
                    }
                }
            }

            $this->get('session')->getFlashBag()->add(
                'notice',
                $nb_veh . '  (Vehicule/s) are/is free'
            );
            $this->get('session')->getFlashBag()->add(
                'notice',
                count($drivers)-$nb_driv . '  (Driver/s) are/is free'
            );
            return $this->render('default/transport/indexa.html.twig');


        }

        return $this->render('default/transport/index.html.twig');


    }
    public function showselectAction()
    {

        return $this->render('default/selectmenu.html.twig');
    }
    public function confirmtypeAction(Request $request)
    {
        if (($request->get('Parent')=='Parent') && ($request->get('Teacher')=='Teacher'))
        {
            $this->get('session')->getFlashBag()->add(
                'notice',
                'You cant choose both'
            );
            return $this->render('default/selectmenu.html.twig');
        }
        else if($request->get('Parent')=='Parent')
        {
            $em=$this->getDoctrine()->getManager();
            $user = $this->getUser();
            //$userManager = $container->get('fos_user.user_manager');
            $user->addRole('ROLE_PARENT');
            $em->flush();
            return $this->redirectToRoute('homepage');
        }
        else if($request->get('Teacher')=='Teacher')
        {
            $em=$this->getDoctrine()->getManager();
            $user = $this->getUser();
            //$userManager = $container->get('fos_user.user_manager');
            $user->addRole('ROLE_TEACHER');
            $em->flush();
            return $this->redirectToRoute('homepage');
        }
        //return new Response('Nice');
    }


}
