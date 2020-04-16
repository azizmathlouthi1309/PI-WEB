<?php

namespace ActivityBundle\Controller;
use ActivityBundle\Entity\Interest ;
use AppBundle\Entity\User;
use FOS\RestBundle\Controller\Annotations\Post;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use ActivityBundle\Entity\Activity;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;


use Symfony\Component\HttpFoundation\Response;


class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('ActivityBundle:default:index.html.twig');
    }

    /**
     * @Route("/act")
     */

    public function indexActAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $user=$this->container->get('security.token_storage')->getToken()->getUser();

        $inter = new Interest();
        if($request->request->get('some_var_name') && $request->request->get('act') && $request->request->get('cID')){
            $arrData = ['act' => $request->request->get('act')  ,'cID' => $user->getId()];

            $parent = $this->getDoctrine()->getRepository(User::class)->find($request->request->get('cID'));
            $myActivity = $this->getDoctrine()->getRepository(Activity::class)->find($request->request->get('act'));
            $inter->setParent($parent);
            $inter->setActivity($myActivity);

            $em->persist($inter);
            $em->flush();

            return new JsonResponse($arrData);

        }
        $parent=$this->getDoctrine()->getRepository(User::class)->find($user->getId());

        $activities = $em->getRepository('ActivityBundle:Activity')->findAll();
        return $this->render('default/activity_class/indexAct.html.twig', array(
            'activities' =>  $activities
        ));
    }


}
