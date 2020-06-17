<?php

namespace EspaceParentBundle\Controller;

use EspaceParentBundle\Entity\Child;
use ClassBundle\Entity\Classe;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use TransportBundle\Entity\Driver;

/**
 * Child controller.
 *
 * @Route("child")
 */
class childController extends Controller
{
    /**
     * Lists all child entities.
     *
     * @Route("/", name="child_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $children = $em->getRepository('EspaceParentBundle:Child')->findAll();

        return $this->render('child/index.html.twig', array(
            'children' => $children,
        ));
    }

    /**
     * Creates a new child entity.
     *
     * @Route("/new", name="child_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $child = new Child();
        $form = $this->createForm('EspaceParentBundle\Form\childType', $child);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $child->uploadProfilePicture();
            $user = $this->container->get('security.token_storage')->getToken()->getUser()->getId();
            if ($user != 'anon.') {
                $child->setParentId($user);
            }
            $em = $this->getDoctrine()->getManager();
            $classes = $em->getRepository(Classe::class)->findAll();
            foreach ($classes as $class) {

                if (($class->getLevel() == $child->getLevel()) && ($class->getNbChild() < 25)) {
                    $class->setNbChild($class->getNbChild() + 1);
                    $child->setClassId($class);

                    $em->persist($child);
                    $em->flush();
                }

            }
            //return new ('no classes available with those conditions');
        }


        //  return $this->redirectToRoute('espace_parent_homepage', array('id' => $child->getId()));


        return $this->render('child/new.html.twig', array(
            'child' => $child,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a child entity.
     *
     * @Route("/{id}", name="child_show")
     * @Method("GET")
     */
    public function showAction(child $child)
    {
        $deleteForm = $this->createDeleteForm($child);

        return $this->render('child/show.html.twig', array(
            'child' => $child,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing child entity.
     *
     * @Route("/modifier/{id}", name="modifier")
     * @Method({"GET", "POST"})
     */
    public function modifierAction(Request $request, child $child)
    {
        $deleteForm = $this->createDeleteForm($child);
        $editForm = $this->createForm('EspaceParentBundle\Form\childType', $child);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('modifier_child');
        }

        return $this->render('child/modifier.html.twig', array(
            'child' => $child,
            'photo' => $child->getPhoto(),
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing child entity.
     *
     * @Route("/{id}/edit", name="child_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, child $child)
    {
        $deleteForm = $this->createDeleteForm($child);
        $editForm = $this->createForm('EspaceParentBundle\Form\childType', $child);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('child_index', array('id' => $child->getId()));
        }

        return $this->render('child/edit.html.twig', array(
            'child' => $child,
            'photo' => $child->getPhoto(),
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a child entity.
     *
     * @Route("/{id}", name="child_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, $id)
    {
        //$form = $this->createDeleteForm($child);
        //$form->handleRequest($request);
        $em = $this->getDoctrine()->getManager();
        //$classes = $em->getRepository(Classe::class)->findOneBy(array('id' => $child->getClassId()));

        $child=$em->getRepository(Child::class)->find($id);
        $classe=$child->getClassId();
            $em->remove($child);
            $classe->setNbChild($classe->getNbChild() - 1);
            $em->flush();


        $children = $em->getRepository('EspaceParentBundle:Child')->findAll();

        return $this->render('child/index.html.twig', array(
            'children' => $children,
        ));
    }

    /**
     * Creates a form to delete a child entity.
     *
     * @param child $child The child entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(child $child)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('child_delete', array('id' => $child->getId())))
            ->setMethod('DELETE')
            ->getForm();
    }

    /**
     * @Route("/affect", name="child_affect")
     */
    public function affectchildAction()
    {
        $child = new Child();
        $form = $this->createFormBuilder($child)
            ->add('class_id', EntityType::class, array('class' => 'ClassBundle:Classe',
                'choice_label' => 'name',
                'multiple' => false))
            ->add('lastname', TextType::class)
            ->add('firstname', TextType::class)
            ->add('level', IntegerType::class)
            ->add('parent_id', IntegerType::class)
            ->add('age', IntegerType::class)
            ->add('photo', TextType::class)
            ->add('Add', SubmitType::class, ['label' => 'Confirm Affectation'])
            ->getForm();
        return $this->render('child/affect.html.twig', ['f' => $form->createView()]);
    }

    public function sendmailAction()
    {
        $transport = (new \Swift_SmtpTransport('smtp.googlemail.com', 465, 'ssl'))
            ->setUsername('ahmed.rais@esprit.tn')
            ->setPassword('mnhsairj');

// Create the Mailer using your created Transport
        $mailer = new \Swift_Mailer($transport);

// Create a message
        $body = 'congratulations your child has been affected in 3thd lions , <p>Email sent through <span style="color:red;">Swift Mailer</span>.</p>';

        $message = (new \Swift_Message(('Email Through Swift Mailer')))
            ->setFrom(['ahmed.rais@esprit.tn.com' => 'KindoGarten'])
            ->setTo(['ahmedrais988@gmail.com'])
            ->setBody($body)
            ->setContentType('text/html');

// Send the message
        $mailer->send($message);
        return $this->redirectToRoute('child_new');
    }

    public function changeclassAction()
    {
        $em = $this->getDoctrine()->getManager();
        $children = $em->getRepository(Child::class)->findAll();
        $thisuserchildren = array();
        $i = 0;
        foreach ($children as $child) {
            if ($child->getParentId() == $this->getUser()->getId()) {
                $thisuserchildren[$i] = $child;
                $i++;
                //return new Response($child->getId());
            } else {
                return new Response('u have no child in our kindo');
            }

        }
        return $this->render('child/affect.html.twig', ["child" => $thisuserchildren]);

    }

    public function confirmnewclassAction(Request $req,$id)
    {
        $child = new Child();
        $em = $this->getDoctrine()->getManager();
        $child = $em->getRepository(Child::class)->find($id);
        $childlevel=$child->getLevel();
        $currentclassid=$child->getClassId()->getId();
        $form = $this->createFormBuilder($child)
            ->add('classid', EntityType::class, array('class' => 'ClassBundle:Classe',
                'choice_label' => 'name',
                'multiple' => false))
            ->add('Add', SubmitType::class, ['label' => 'Confirm Edit'])
            ->getForm();
        $form->handleRequest($req);
        if ($form->isSubmitted() && $form->isValid()) {
            if($childlevel==$child->getClassId()->getLevel())
            {
                //return new Response('hahahahahaha');
                if($child->getClassId()->getNbchild()<25)
                {

                    $oldclass=$em->getRepository(Classe::class)->find($currentclassid);
                    $oldclass->setNbChild($oldclass->getNbchild()-1);
                    $child->getClassId()->setNbchild($child->getClassId()->getNbchild()+1);
                    $em->flush();

                }
                else
                {
                    return new Response('class full');
                }
            }
            else
            {
                return new Response('not same level');
            }
        }
        return $this->render('child/change.html.twig', ['f' => $form->createView()]);


    }

}