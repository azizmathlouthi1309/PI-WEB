<?php

namespace EventBundle\Controller;

use EventBundle\Entity\Event;
use EventBundle\Entity\Forum;
use EventBundle\Entity\Likes;
use EventBundle\Entity\Participation;
use EventBundle\Form\EventType;
use EventBundle\Form\ForumType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Filesystem\Filesystem;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\HttpFoundation\Request;

class EventController extends Controller
{
    public function addAction(Request $request)
    {
        $event = new Event();
        $form = $this->createForm(EventType::class, $event);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /**
             * @var UploadedFile $file
             */
            $file = $event->getPicture();
            $fileName = md5(uniqid() . '.' . $file->guessExtension());
            $file->move($this->getParameter('image'), $fileName);
            $event->setPicture($fileName);
            #$event->upload();
            $ef = $this->getDoctrine()->getManager();
            $ef->persist($event);
            $ef->flush();

            return $this->redirectToRoute('event_read');

        }
        return $this->render("@Event/Event/add.html.twig", array("eventF" => $form->createView(),));
    }

    public function readAction(Request $request)
    {

        $events = $this->getDoctrine()
            ->getRepository(Event::class)
            ->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $events,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 4)
        );

        return $this->render('@Event/Event/read.html.twig', array('events' => $result,));
    }

    public function deleteAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $event = $em->find('EventBundle:Event', $id);

        $image = $event->getPicture();

        $path = $this->getParameter('image') . '/' . $image;
        $f = new Filesystem();
        $f->remove(array($path));

        $em->remove($event);
        $em->flush();

        return $this->redirectToRoute('event_read', ['event' => $event->getId(),]);
    }

    public function UpdateAction(Request $request, $id)
    {

        $message = "Edit event";
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository('EventBundle:Event')->find($id);

        $form = $this->createForm(EventType::class, $event);
        #$request=$this->getRequest();

        $form->handleRequest($request);

        if ($form->isValid() && $form->isSubmitted()) {
            /**
             * @var UploadedFile $file
             */
            $file = $event->getPicture();
            $fileName = md5(uniqid() . '.' . $file->guessExtension());
            $file->move($this->getParameter('image'), $fileName);
            $event->setPicture($fileName);
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute('event_read');
        }


        return $this->render("@Event/Event/edit.html.twig", array("eventF" => $form->createView(), 'msg' => $message));
    }

    public function searchAction(Request $request)
    {

        $motcle = $request->get('motcle');
        $query = $this->getDoctrine()->getRepository('EventBundle:Event')->createQueryBuilder('e');
        $query->where('e.name like :name')->setParameter('name', $motcle . '%');
        $query = $query->orderBy('e.name', 'ASC')->getQuery();
        $events = $query->getResult();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $events,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 4)
        );

        return $this->render('@Event/Event/read.html.twig', array('events' => $result));

    }

    public function readFAction(Request $request)
    {

        $events = $this->getDoctrine()
            ->getRepository(Event::class)
            ->findAll();


        return $this->render('@Event/Event/readF.html.twig', array('events' => $events,));
    }

    public function readForumAction(Request $request)
    {
        $forums = $this->getDoctrine()
            ->getRepository(Forum::class)
            ->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $forums,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 4)
        );

        return $this->render('@Event/Forum/forum.html.twig', array('forums' => $result,));
    }

    public function detailAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $p = $em->getRepository('EventBundle:Event')->find($id);
        return $this->render('@Event/Event/more.html.twig', array(
            'id'=>$p->getId(),
            'name' => $p->getName(),
            'description' => $p->getDescription(),
            'date' => $p->getDate(),
            'hourBegin' => $p->getHourBegin(),
            'hourEnd' => $p->getHourEnd(),
            'picture' => $p->getPicture(),
            'events'=>$p,
            'forums'=>$p->getForums(),

        ));
    }


    public function deleteForumAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $forum = $em->find('EventBundle:Forum', $id);

        $em->remove($forum);
        $em->flush();

        return $this->redirectToRoute('forum_read');
    }
    public function deleteForumFAction(Request $request)
    {
        $ref = $request->headers->get('referer');
        $id = $request->get('id');
        $em= $this->getDoctrine()->getManager();
        $forum=$em->getRepository('EventBundle:Forum')->find($id);
        $em->remove($forum);
        $em->flush();

        return $this->redirect($ref);
    }

    public function ParticipationAction(Request $request, UserInterface $user,$id)
    {

        $ref = $request->headers->get('referer');
        $event = $this->getDoctrine()->getRepository('EventBundle:Event')->find($id);
        $event->setCapacity($event->getCapacity() - 1);

        $par = new Participation();

        $par->setParentId($user);
        $par->setEvent($event);
        $em = $this->getDoctrine()->getManager();
        $em->persist($par);
        $em->flush();

        return $this->redirect($ref);
    }
    public function DelayAction(Request $request)
    {
        $ref = $request->headers->get('referer');
        $id = $request->get('id');
        $em= $this->getDoctrine()->getManager();
        $parti=$em->getRepository('EventBundle:Participation')->find($id);
        $event = $this->getDoctrine()->getRepository('EventBundle:Event')->find($id);
        $event->setCapacity($event->getCapacity() + 1);
        $parti->setEvent($event);
        $em->remove($parti);
        $em->flush();

        return $this->redirect($ref);
    }
    public function readParAction(Request $request)
    {

        $participations = $this->getDoctrine()
            ->getRepository(Participation::class)
            ->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        $result = $paginator->paginate(
            $participations,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 4)
        );

        return $this->render('@Event/Parti/parti.html.twig', array('participations' => $result,));
    }
    public function addForumAction(Request $request, UserInterface $user,$id )
    {
        $ref = $request->headers->get('referer');
        #$event= $this->getDoctrine()->getRepository(Event::class)->findById($this->$request->get('event_id'));
        $event = $this->getDoctrine()->getRepository('EventBundle:Event')->find($id);
        $forum = new Forum();

        $forum->setUser($user);
        $forum->setEvent($event);
        $forum->setMessage($request->get('message'));
        #$forum->setMessage($request->request->get('message'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($forum);
        $em->flush();

        #$this->addFlash('info', 'Comment published !.');

        return $this->redirect($ref);

    }
    public function forumFAction(Request $request,$id)
    {
        $ref = $request->headers->get('referer');
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository(Event::class)->findby($id);
        if($event)
        {
            $forum = $em->getRepository(Forum::class)->findAll();
            return $this->redirect($ref, array('forums' => $forum,));
        }


    }
    public function likeAction($id, UserInterface $user,Request $request)
    {
        $ref = $request->headers->get('referer');
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository('EventBundle:Event')->find($id);
        $like = $em->getRepository('EventBundle:Likes')->findOneBy(array('eventId' => $id, 'parentId' => $user->getId()));

        if ($event && !$like) {
            $like = new Likes();
            $like->setEventId($event);
            $like->setParentId($user);
            $em->persist($like);
            $em->flush();

            $event->setNbre($event->getNbre($id) + 1);
            $em->flush();

            return $this->redirect($ref);
        }
    }
    public function dislikeAction($id, UserInterface $user,Request $request)
    {
        $ref = $request->headers->get('referer');
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository('EventBundle:Event')->find($id);

        if ($event)
        {
            $like = $em->getRepository('EventBundle:Likes')->findOneBy(array('eventId'=>$id, 'parentId'=>$user->getId()));
            if($like) {
                $em->remove($like);
                $event->setNbre($event->getNbre($id) - 1);
                $em->flush();
            }
            return $this->redirect($ref);
        }

        return $this->redirect($ref);
    }
}