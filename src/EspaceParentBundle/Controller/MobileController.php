<?php


namespace EspaceParentBundle\Controller;


use AppBundle\Entity\User;
use ClassBundle\Entity\Classe;
use ClassBundle\Entity\Teacher;
use EspaceParentBundle\Entity\Child;
use EspaceParentBundle\Entity\view;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use TransportBundle\Entity\Vehicule;

class MobileController extends Controller
{
    public function addviewAction(Request $req)
    {
        $em=$this->getDoctrine()->getManager();
        $view=new view();
        $view->setSenderName($req->get('sender'));
        $view->setMessage($req->get('message'));
        $view->setNbStars($req->get('nbstars'));
        $view->setSubject($req->get('subject'));
        $em->persist($view);
        $em->flush();
        return new Response($view->getNbStars());
    }
    public function addchildAction(Request $req)
    {
        $em=$this->getDoctrine()->getManager();
        $view=new Child();
        $view->setFirstname($req->get('firstname'));
        $view->setLastname($req->get('lastname'));
        $view->setLevel($req->get('level'));
        $view->setParentId($req->get('parent'));
        $classes=$em->getRepository(Classe::class)->findAll();

        foreach ($classes as $class)
        {
            if($class->getLevel()==$view->getLevel())
            {
                $view->setClassId($class);
            }
        }
        $view->setAge($req->get('age'));
        $view->setPhoto($req->get('photo'));
        $em->persist($view);
        $em->flush();
        return new Response(1);
    }
    public function showchildAction()
    {

        $traffectaions = $this->getDoctrine()->getManager()->getRepository(Child::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($traffectaions);
        return new JsonResponse($formatted);
    }
    public function showviewAction()
    {
        $traffectaions = $this->getDoctrine()->getManager()->getRepository(view::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($traffectaions);
        return new JsonResponse($formatted);
    }
}