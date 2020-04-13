<?php

namespace EventBundle\Controller;

use EventBundle\Entity\Forum;
use EventBundle\Form\ForumType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\Request;

class ForumController extends Controller
{
    public function addAction(Request $request)
    {
        $ef = $this->getDoctrine()->getManager();
        $forum = new Forum();
        $form = $this->createForm(ForumType::class, $forum);

            $form->handleRequest($request);

            if ($form->isSubmitted() && $form->isValid())
            {
                $ef->persist($forum);
                $ef->flush();

                return $this->redirectToRoute('event_readF');
            }

        return $this->render("@Event/Event/readF.html.twig", array("forum" => $form->createView(),));
    }
    public function readAction(Request $request)
    {
        $forums=$this->getDoctrine()
            ->getRepository(Forum::class)
            ->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result= $paginator->paginate(
            $forums,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );

        return $this->render('@Event/Forum/read.html.twig',array('forums'=>$result, ));
    }
    public function deleteAction( $id){

        $em=$this->getDoctrine()->getManager();
        $forum=$em->find('EventBundle:Forum',$id);
        $em->remove($forum);
        $em->flush();

        return $this->redirectToRoute('event_read',['forum'=> $forum->getId(),]);
    }
    public function deleteFAction( $id){

        $em=$this->getDoctrine()->getManager();
        $forum=$em->find('EventBundle:Forum',$id);
        $em->remove($forum);
        $em->flush();

        return $this->redirectToRoute('forum_read',['forum'=> $forum->getId(),]);
    }
    public function readFAction(Request $request)
    {

        $forums=$this->getDoctrine()
            ->getRepository(Forum::class)
            ->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result= $paginator->paginate(
            $forums,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',5)
        );

        return $this->render('@Event/Forum/read.html.twig',array('events'=>$result, ));
    }
}
