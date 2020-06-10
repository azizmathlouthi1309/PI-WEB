<?php

namespace EspaceParentBundle\Controller;

use EspaceParentBundle\Entity\complaint;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;use Symfony\Component\HttpFoundation\Request;

/**
 * Complaint controller.
 *
 * @Route("complaint")
 */
class complaintController extends Controller
{
    /**
     * Lists all complaint entities.
     *
     * @Route("/", name="complaint_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $complaints = $em->getRepository('EspaceParentBundle:complaint')->findAll();

        return $this->render('complaint/index.html.twig', array(
            'complaints' => $complaints,
        ));
    }

    /**
     * Creates a new complaint entity.
     *
     * @Route("/new", name="complaint_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $complaint = new Complaint();
        $form = $this->createForm('EspaceParentBundle\Form\complaintType', $complaint);
        $form->handleRequest($request);
        $user = $this->container->get('security.token_storage')->getToken()->getUser()->getId();
        if($user != 'anon.')
        {
            $complaint->setParentId($user);
            $complaint->setStatus(0);

        }
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($complaint);
            $em->flush();

            return $this->redirectToRoute('espace_parent_homepage');
        }

        return $this->render('complaint/new.html.twig', array(
            'complaint' => $complaint,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a complaint entity.
     *
     * @Route("/{id}", name="complaint_show")
     * @Method("GET")
     */
    public function showAction(complaint $complaint)
    {
        $deleteForm = $this->createDeleteForm($complaint);

        return $this->render('complaint/show.html.twig', array(
            'complaint' => $complaint,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing complaint entity.
     *
     * @Route("/{id}/edit", name="complaint_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, complaint $complaint)
    {
        $deleteForm = $this->createDeleteForm($complaint);
        $editForm = $this->createForm('EspaceParentBundle\Form\complaintType', $complaint);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $complaint->setStatus(1);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('complaint_list');
        }

        return $this->render('complaint/edit.html.twig', array(
            'complaint' => $complaint,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a complaint entity.
     *
     * @Route("/{id}", name="complaint_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request,$id)
    {

            $em = $this->getDoctrine()->getManager();
            $comp=$em->getRepository(complaint::class)->find($id);
            $em->remove($comp);
            $em->flush();


        return $this->redirectToRoute('complaint_index');
    }

    /**
     * Creates a form to delete a complaint entity.
     *
     * @param complaint $complaint The complaint entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(complaint $complaint)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('complaint_delete', array('id' => $complaint->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
