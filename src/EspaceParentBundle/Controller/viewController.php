<?php

namespace EspaceParentBundle\Controller;

use EspaceParentBundle\Entity\view;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * View controller.
 *
 * @Route("view")
 */
class viewController extends Controller
{
    /**
     * Lists all view entities.
     *
     * @Route("/", name="view_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $views = $em->getRepository('EspaceParentBundle:view')->findAll();

        return $this->render('view/index.html.twig', array(
            'views' => $views,
        ));
    }

    /**
     * Creates a new view entity.
     *
     * @Route("/new", name="view_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $view = new View();
        $form = $this->createForm('EspaceParentBundle\Form\viewType',$view);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            //return new JsonResponse($view->getNbStars());
            $em = $this->getDoctrine()->getManager();
            $em->persist($view);
            $em->flush();
            //return new Response($view);

            return $this->redirectToRoute('espace_parent_homepage', array('id' => $view->getId()));
        }

        return $this->render('view/new.html.twig', array(
            'view' => $view,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a view entity.
     *
     * @Route("/{id}", name="view_show")
     * @Method("GET")
     */
    public function showAction(view $view)
    {
        $deleteForm = $this->createDeleteForm($view);
        $em = $this->getDoctrine()->getManager();

        $views = $em->getRepository('EspaceParentBundle:view')->findAll();
        return $this->render('view/show.html.twig', array(
            'views' => $views,

        ));
    }

    /**
     * Displays a form to edit an existing view entity.
     *
     * @Route("/{id}/edit", name="view_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, view $view)
    {
        $deleteForm = $this->createDeleteForm($view);
        $editForm = $this->createForm('EspaceParentBundle\Form\viewType', $view);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('view_edit', array('id' => $view->getId()));
        }

        return $this->render('view/edit.html.twig', array(
            'view' => $view,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a view entity.
     *
     * @Route("/{id}", name="view_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, view $view)
    {
        $form = $this->createDeleteForm($view);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($view);
            $em->flush();

        }
        return $this->render('view/index.html.twig');
    }

    /**
     * Creates a form to delete a view entity.
     *
     * @param view $view The view entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(view $view)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('view_delete', array('id' => $view->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function delleteview2Action(Request $req,$id)
    {
        $em=$this->getDoctrine()->getManager();
        $views=$em->getRepository(view::class)->findAll();
        $view=$em->getRepository(view::class)->find($id);
        $em->remove($view);
        $em->flush();
        return $this->redirectToRoute('view_list');
    }
}
